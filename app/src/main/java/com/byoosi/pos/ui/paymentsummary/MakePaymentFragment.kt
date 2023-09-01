package com.byoosi.pos.ui.paymentsummary

import android.app.DatePickerDialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import com.byoosi.pos.R
import com.byoosi.pos.data.network.RequestInterface
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.*

class MakePaymentFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.make_payment, container, false)
        val requestInterface = RequestInterface.getInstance()

        val etPostingDate = view.findViewById<EditText>(R.id.etPostingDate)
        etPostingDate.setOnClickListener {
            showDatePickerDialog(etPostingDate)
        }

        val etParty = view.findViewById<AutoCompleteTextView>(R.id.etParty)
        val etModeOfPayment = view.findViewById<AutoCompleteTextView>(R.id.etModeOfPayment)

        fetchCustomers(requestInterface, etParty)
        fetchPaymentModes(requestInterface, etModeOfPayment)

        val etPaidAmount = view.findViewById<EditText>(R.id.etPaidAmount)
        val etRemarks = view.findViewById<EditText>(R.id.etRemarks)

        val btnContinue = view.findViewById<Button>(R.id.btnContinue)
        btnContinue.setOnClickListener {
            createCustomerPayment(requestInterface, etParty, etModeOfPayment, etPostingDate, etPaidAmount, etRemarks)
        }

        return view
    }

    private fun showDatePickerDialog(editText: EditText) {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog = DatePickerDialog(
            requireContext(),
            { _, selectedYear, selectedMonth, selectedDay ->
                val formattedDate = String.format("%02d-%02d-%02d", selectedYear, selectedMonth + 1, selectedDay)
                editText.setText(formattedDate)
            },
            year,
            month,
            day
        )
        datePickerDialog.show()
    }

    private fun fetchCustomers(requestInterface: RequestInterface, etParty: AutoCompleteTextView) {
        GlobalScope.launch(Dispatchers.Main) {
            try {
                val customersResponse = withContext(Dispatchers.IO) {
                    requestInterface.getCustomersPay(mapOf()) // You might need to pass appropriate parameters
                }

                val customers = customersResponse.message
                val customerNames = customers.mapNotNull { it.name } // Extract customer names

                // Update AutoCompleteTextView adapter with fetched customer names
                val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_dropdown_item, customerNames)
                etParty.setAdapter(adapter)

            } catch (e: Exception) {
                Log.e("Test", "Exception: ${e.message}")
                // Handle any exceptions that occurred during the network request
                // TODO: Handle the exception, show an error message, etc.
            }
        }
    }

    private fun fetchPaymentModes(requestInterface: RequestInterface, etModeOfPayment: AutoCompleteTextView) {
        val paymentModeNames = mutableListOf<String>() // Initialize an empty list to hold payment mode names

        GlobalScope.launch(Dispatchers.Main) {
            try {
                val paymentModesResponse = withContext(Dispatchers.IO) {
                    requestInterface.getModeOfPayment()
                }

                val paymentModes = paymentModesResponse.message
                paymentModeNames.addAll(paymentModes.mapNotNull { it.name }) // Extract and add payment mode names

                // Set the adapter, but don't show suggestions immediately
                val paymentModeAdapter = ArrayAdapter(
                    requireContext(),
                    android.R.layout.simple_dropdown_item_1line,
                    paymentModeNames
                )
                etModeOfPayment.setAdapter(paymentModeAdapter)
                etModeOfPayment.threshold = 1 // Show suggestions only when at least 1 character is typed

            } catch (e: Exception) {
                Log.e("Test", "Exception: ${e.message}")
                // Handle any exceptions that occurred during the network request
                // TODO: Handle the exception, show an error message, etc.
            }
        }
    }


    private fun createCustomerPayment(
        requestInterface: RequestInterface,
        etParty: AutoCompleteTextView,
        etModeOfPayment: AutoCompleteTextView,
        etPostingDate: EditText,
        etPaidAmount: EditText,
        etRemarks: EditText
    ) {
        val postingDate = etPostingDate.text.toString()
        val paidAmount = etPaidAmount.text.toString()
        val remarks = etRemarks.text.toString()

        val selectedCustomer = etParty.text.toString()
        val selectedPaymentMode = etModeOfPayment.text.toString()

        if (postingDate.isNotEmpty() && selectedCustomer.isNotEmpty() && selectedPaymentMode.isNotEmpty() &&
            paidAmount.isNotEmpty() && remarks.isNotEmpty()
        ) {
            GlobalScope.launch(Dispatchers.Main) {
                try {
                    val response = withContext(Dispatchers.IO) {
                        requestInterface.makePayment(
                            mapOf(
                                "posting_date" to postingDate,
                                "customer" to selectedCustomer,
                                "amount" to paidAmount,
                                "mode_of_payment" to selectedPaymentMode,
                                "remarks" to remarks
                            )
                        )
                    }

                    // Handle the response and update UI
                    val salesPaymentSummary = response.message
                    showToast("Payment posted successfully!")
                    // Clear the EditText fields after successful payment
                    etPostingDate.text.clear()
                    etParty.text.clear()
                    etModeOfPayment.text.clear()
                    etPaidAmount.text.clear()
                    etRemarks.text.clear()
                  } catch (e: Exception) {
                    Log.e("Test", "Exception: ${e.message}")
                    // Handle any exceptions that occurred during the network request
                    // TODO: Handle the exception, show an error message, etc.
                }
            }
        } else {
            // Prompt the user to fill in required fields by setting error messages
            if (postingDate.isEmpty()) {
                etPostingDate.error = "Posting date is required"
            }
            if (selectedCustomer.isEmpty()) {
                etParty.error = "Customer is required"
            }
            if (selectedPaymentMode.isEmpty()) {
                etModeOfPayment.error = "Payment mode is required"
            }
            if (paidAmount.isEmpty()) {
                etPaidAmount.error = "Paid amount is required"
            }
            if (remarks.isEmpty()) {
                etRemarks.error = "Remarks are required"
            }
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }
}

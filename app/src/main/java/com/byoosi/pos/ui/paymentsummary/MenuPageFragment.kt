package com.byoosi.pos.ui.paymentsummary

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.byoosi.pos.R
import com.byoosi.pos.ui.PaymentActivityFragment
import com.byoosi.pos.ui.home.cart.CartFragment
import com.byoosi.pos.ui.home.customer.CustomerFragment
import com.byoosi.pos.ui.home.invoice.InvoiceFragment
import com.byoosi.pos.ui.home.product.ProductsFragment

class MenuPageFragment : Fragment() {

        override fun onCreateView(
                inflater: LayoutInflater, container: ViewGroup?,
                savedInstanceState: Bundle?
        ): View? {
                val view = inflater.inflate(R.layout.activity_dash_board_menu, container, false)

                // Assuming you have a CardView with the ID "cardViewSalesSummary" in your layout
                val cardView = view.findViewById<CardView>(R.id.cardViewSalesSummary)
                cardView.setOnClickListener {
                        // Create a new instance of PaymentActivityFragment
                        val targetFragment = PaymentActivityFragment()

                        // Get the FragmentManager
                        val fragmentManager: FragmentManager = requireFragmentManager()

                        // Begin a fragment transaction
                        val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()

                        // Set the background color of the fragmentContainerPayment to blue
                        val fragmentContainerPayment = view.findViewById<View>(R.id.fragmentContainerPayment)
                        fragmentContainerPayment.setBackgroundColor(resources.getColor(android.R.color.holo_blue_light))

                        // Replace MenuPageFragment with PaymentActivityFragment
                        fragmentTransaction.replace(R.id.fragmentContainerPayment, targetFragment)

                        // Commit the transaction
                        fragmentTransaction.commit()
                }
                val cardViewPayments = view.findViewById<CardView>(R.id.cardViewPayments)
                cardViewPayments.setOnClickListener {
                        // Create a new instance of PaymentActivityFragment
                        val targetFragment = MakePaymentFragment()

                        // Get the FragmentManager
                        val fragmentManager: FragmentManager = requireFragmentManager()

                        // Begin a fragment transaction
                        val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()

                        // Set the background color of the fragmentContainerPayment to blue
                        val fragmentContainerPayment = view.findViewById<View>(R.id.fragmentContainerPayment)
                        fragmentContainerPayment.setBackgroundColor(resources.getColor(android.R.color.holo_blue_light))

                        // Replace MenuPageFragment with PaymentActivityFragment
                        fragmentTransaction.replace(R.id.fragmentContainerPayment, targetFragment)

                        // Commit the transaction
                        fragmentTransaction.commit()
                }
                val cardViewCustomers = view.findViewById<CardView>(R.id.cardViewCustomers)
                cardViewCustomers.setOnClickListener {
                        // Create a new instance of PaymentActivityFragment
                        val targetFragment = CustomerFragment()

                        // Get the FragmentManager
                        val fragmentManager: FragmentManager = requireFragmentManager()

                        // Begin a fragment transaction
                        val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()

                        // Set the background color of the fragmentContainerPayment to blue
                        val fragmentContainerPayment = view.findViewById<View>(R.id.fragmentContainerPayment)
                        fragmentContainerPayment.setBackgroundColor(resources.getColor(android.R.color.holo_blue_light))

                        // Replace MenuPageFragment with PaymentActivityFragment
                        fragmentTransaction.replace(R.id.fragmentContainerPayment, targetFragment)

                        // Commit the transaction
                        fragmentTransaction.commit()
                }
                val cardViewProducts = view.findViewById<CardView>(R.id.cardViewProducts)
                cardViewProducts.setOnClickListener {
                        // Create a new instance of PaymentActivityFragment
                        val targetFragment = ProductsFragment()

                        // Get the FragmentManager
                        val fragmentManager: FragmentManager = requireFragmentManager()

                        // Begin a fragment transaction
                        val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()

                        // Set the background color of the fragmentContainerPayment to blue
                        val fragmentContainerPayment = view.findViewById<View>(R.id.fragmentContainerPayment)
                        fragmentContainerPayment.setBackgroundColor(resources.getColor(android.R.color.holo_blue_light))

                        // Replace MenuPageFragment with PaymentActivityFragment
                        fragmentTransaction.replace(R.id.fragmentContainerPayment, targetFragment)

                        // Commit the transaction
                        fragmentTransaction.commit()
                }
                val cardViewInvoices = view.findViewById<CardView>(R.id.cardViewInvoices)
                cardViewInvoices.setOnClickListener {
                        // Create a new instance of PaymentActivityFragment
                        val targetFragment = InvoiceFragment()

                        // Get the FragmentManager
                        val fragmentManager: FragmentManager = requireFragmentManager()

                        // Begin a fragment transaction
                        val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()

                        // Set the background color of the fragmentContainerPayment to blue
                        val fragmentContainerPayment = view.findViewById<View>(R.id.fragmentContainerPayment)
                        fragmentContainerPayment.setBackgroundColor(resources.getColor(android.R.color.holo_blue_light))

                        // Replace MenuPageFragment with PaymentActivityFragment
                        fragmentTransaction.replace(R.id.fragmentContainerPayment, targetFragment)

                        // Commit the transaction
                        fragmentTransaction.commit()
                }

                return view
        }
}

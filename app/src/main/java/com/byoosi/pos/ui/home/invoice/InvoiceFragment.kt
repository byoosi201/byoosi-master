package com.byoosi.pos.ui.home.invoice

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.byoosi.pos.base.BaseFragment
import com.byoosi.pos.ui.invoice.InvoiceDetailActivity
import com.byoosi.pos.R
import kotlinx.android.synthetic.main.fragment_invoice.*
import org.jetbrains.anko.startActivity

/**
 * Created by pintusingh on 24/12/20.
 */

class InvoiceFragment : BaseFragment(R.layout.fragment_invoice) {
    private lateinit var adapterInvoice: InvoiceAdapter
    private var isLoading = true

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
        setOnClick()
    }

    private fun init() {
        adapterInvoice = InvoiceAdapter().also { getInvoices(0) }
        rvInvoice.adapter = adapterInvoice
        rvInvoice.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (!isLoading &&
                    (rvInvoice.layoutManager as LinearLayoutManager).findLastCompletelyVisibleItemPosition() == adapterInvoice.itemCount - 1
                ) {
                    getInvoices(adapterInvoice.itemCount)
                    isLoading = true
                }
            }
        })
    }

    private fun setOnClick() {
        adapterInvoice.setItemClickListener { view, i, invoiceItem ->
            when (view.id) {
                R.id.llInvoice -> requireActivity().startActivity<InvoiceDetailActivity>("invoice_id" to invoiceItem.name)
            }
        }
    }

    override fun onResume() {
        super.onResume()
        getInvoices(0)
    }

    private fun getInvoices(offset: Int) {
        callApi(offset == 0, {
            requestInterface.getInvoiceList(mapOf<String, Any>("offset" to offset, "limit" to 10))
        }, { response ->
            adapterInvoice.run {
                if (offset == 0) addAll(response.message) else appendAll(response.message)
            }
            isLoading = false
        })
    }
}
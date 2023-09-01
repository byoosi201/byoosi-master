package com.byoosi.pos

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.byoosi.pos.ui.PaymentActivityFragment
import com.byoosi.pos.ui.home.customer.CustomerFragment
import com.byoosi.pos.ui.home.product.ProductsFragment
import com.byoosi.pos.ui.paymentsummary.MakePaymentFragment

class DashBoardMenu : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dash_board_menu)

        val fragmentManager: FragmentManager = supportFragmentManager

        // Set click listeners for each card view
//        findViewById<View>(R.id.cardViewInvoices).setOnClickListener {
//            navigateToFragment(InvoiceDetailFragment(), fragmentManager)
//        }

        findViewById<View>(R.id.cardViewCustomers).setOnClickListener {
            navigateToFragment(CustomerFragment(), fragmentManager)
        }

        findViewById<View>(R.id.cardViewPayments).setOnClickListener {
            navigateToFragment(MakePaymentFragment(), fragmentManager)
        }

        findViewById<View>(R.id.cardViewSalesSummary).setOnClickListener {
            navigateToFragment(PaymentActivityFragment(), fragmentManager)
        }

        findViewById<View>(R.id.cardViewProducts).setOnClickListener {
            navigateToFragment(ProductsFragment(), fragmentManager)
        }

//        findViewById<View>(R.id.cardViewHome).setOnClickListener {
//            navigateToFragment(HomeFragment(), fragmentManager)
//        }
    }

    private fun navigateToFragment(fragment: androidx.fragment.app.Fragment, fragmentManager: FragmentManager) {
        val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragmentContainerPayment, fragment)
        fragmentTransaction.addToBackStack(null) // Optional: Add to back stack
        fragmentTransaction.commit()
    }
}

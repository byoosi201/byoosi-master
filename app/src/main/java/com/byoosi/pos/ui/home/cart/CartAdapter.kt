package com.byoosi.pos.ui.home.cart

import android.app.AlertDialog
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.EditText
import com.byoosi.pos.R
import com.byoosi.pos.base.BaseAdapter
import com.byoosi.pos.model.ProductItem
import kotlinx.android.synthetic.main.listitem_cart.view.*

class CartAdapter : BaseAdapter<ProductItem>(R.layout.listitem_cart) {
    override fun setClickableView(itemView: View): List<View> {
        return listOf(itemView.ivAdd, itemView.ivRemove, itemView.tvQuantity)
        return listOf(itemView.ivAdd, itemView.ivRemove, itemView.tvPrice)
    }

    override fun onBind(view: View, position: Int, item: ProductItem, payloads: MutableList<Any>) {
        view.run {
            tvTitle.text = item.item_name
            tvSubTitle.text = Html.fromHtml(item.description)
            tvPrice.text = context.getString(R.string.price, item.price)
            tvTotalPrice.text = context.getString(R.string.price, item.price * item.quantity)
            tvQuantity.text = item.quantity.toString()

            tvPrice.setOnClickListener {
                val dialogView = LayoutInflater.from(context).inflate(R.layout.dialog_update_price, null)
                val etNewPrice = dialogView.findViewById<EditText>(R.id.etNewPrice)
                val btnUpdatePrice = dialogView.findViewById<Button>(R.id.btnUpdatePrice)

                etNewPrice.setText(item.price.toString())

                val dialog = AlertDialog.Builder(context)
                    .setTitle("Update Price")
                    .setView(dialogView)
                    .create()

                btnUpdatePrice.setOnClickListener {
                    val updatedPrice = etNewPrice.text.toString().toDoubleOrNull() ?: item.price
                    item.price = updatedPrice
                    tvPrice.text = context.getString(R.string.price, updatedPrice)

                    // Calculate and update total price
                    val newTotalPrice = updatedPrice * item.quantity
                    tvTotalPrice.text = context.getString(R.string.price, newTotalPrice)
                    dialog.dismiss()
                }

                dialog.show()
            }
        }
    }

}
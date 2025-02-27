package com.byoosi.pos.model

import java.io.Serializable

data class ProductItem(
    val item_code: String = "",
    val item_name: String = "",
    val description: String = "",
    val stock: Double = 0.0,
    val stock_uom: String = "",
    var price: Double = 0.0,
    var quantity: Int = 0,
    var isExpanded: Boolean = false,
    var other_warehouse_stock: List<StockItem> = arrayListOf(),
    var updatedPrice: Double = 0.0
) : Serializable{
    val rate: Double
        get() = price * quantity
}
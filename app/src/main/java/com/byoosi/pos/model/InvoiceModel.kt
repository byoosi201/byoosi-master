package com.byoosi.pos.model

import java.io.Serializable


/**
 * Created by pintusingh on 26/12/20.
 */

data class PaymentMode(val name: String)

data class InvoiceItem(
    val name: String,
    val customer: String,
    val customer_name: String,
    val grand_total: Double = 0.0,
    val paid_amount: Double = 0.0,
    val outstanding_amount: Double = 0.0,
    val docstatus: Int = 0
) : Serializable

class InvoiceResponse(val message: InvoiceDetail) : Serializable
class InvoiceContentResponse(val message: String) : Serializable

data class InvoiceDetail(
    val name: String,
    val customer: String?,
    val customer_name: String?,
    val contact_mobile: String?,
    val contact_email: String?,
    val total_qty: Double,
    var total: Double,
    val grand_total: Double,
    var outstanding_amount: Double,
    var paid_amount: Double,
    val items: List<InvoiceProduct>,
    val payments: List<InvoicePayment>,
    val taxes: List<InvoiceTax>
) : Serializable


class InvoiceProduct(
    val name: String?,
    val item_code: String,
    val item_name: String?,
    val description: String?,
    val stock_uom: String,
    var qty: Double,
    var amount: Double,
    var rate: Double,
    val taxes: List<InvoiceTax>?
) : Serializable

class InvoicePayment(
    val name: String?,
    val mode_of_payment: String,
    val amount: Double,
) : Serializable

class InvoiceTax(
    val charge_type: String,
    val account_head: String,
    val description: String?,
    val rate: Double,
    val tax_amount: Double
) : Serializable
package com.byoosi.pos.data.pref


import android.content.Context
import com.byoosi.pos.R
import com.byoosi.pos.model.Login
import com.byoosi.pos.model.ProductItem
import com.chibatching.kotpref.KotprefModel
import com.chibatching.kotpref.gsonpref.gsonNullablePref
import com.chibatching.kotpref.gsonpref.gsonPref

/**
 * Created by pintusingh on 20/12/20.
 */

object SharedPref : KotprefModel() {
    var isLogin by booleanPref()
    var apiKey by stringPref()
    var apiSecret by stringPref()
    var login by gsonNullablePref<Login>()
    var cartItems by gsonPref<ArrayList<ProductItem>>(arrayListOf())
    var customBaseUrl by stringPref() // Default BASE_URL

    fun setLoginData(data: Login) {
        login = data
        isLogin = true
        apiKey = data.message?.api_key ?: ""
        apiSecret = data.message?.api_secret ?: ""
    }

    fun clearPref() = clear()

}


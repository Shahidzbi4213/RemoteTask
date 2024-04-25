package com.gulehri.remotetask.data.utils

import okhttp3.ResponseBody
import org.json.JSONObject

object Utils {

    fun ResponseBody?.getErrorMessage(): String = this?.charStream()?.readText()?.let {
        JSONObject(it).getString("message")
    } ?: "Error Occurred"
}

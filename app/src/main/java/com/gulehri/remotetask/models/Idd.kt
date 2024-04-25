package com.gulehri.remotetask.models


import com.google.gson.annotations.SerializedName

data class Idd(
    @SerializedName("root")
    val root: String, // +9
    @SerializedName("suffixes")
    val suffixes: List<String>
)
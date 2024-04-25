package com.gulehri.remotetask.models


import com.google.gson.annotations.SerializedName

data class Name(
    @SerializedName("common")
    val common: String, // Pakistan
    @SerializedName("official")
    val official: String // Islamic Republic of Pakistan
)
package com.gulehri.remotetask.models


import com.google.gson.annotations.SerializedName

data class CountryItem(
    @SerializedName("capital")
    val capital: List<String>,
    @SerializedName("capitalInfo")
    val capitalInfo: CapitalInfo,
    @SerializedName("flags")
    val flags: Flags,
    @SerializedName("idd")
    val idd: Idd,
    @SerializedName("name")
    val name: Name,
    @SerializedName("unMember")
    val unMember: Boolean
)
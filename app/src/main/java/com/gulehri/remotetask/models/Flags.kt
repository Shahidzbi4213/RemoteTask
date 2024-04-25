package com.gulehri.remotetask.models


import com.google.gson.annotations.SerializedName

data class Flags(
    @SerializedName("alt")
    val alt: String, // The flag of Pakistan is composed of a white vertical band on its hoist side that takes up about one-fourth the width of the field and a dark green rectangular area that spans the rest of the field. A white fly-side facing crescent and five-pointed star are centered in the dark green area.
    @SerializedName("png")
    val png: String, // https://flagcdn.com/w320/pk.png
    @SerializedName("svg")
    val svg: String // https://flagcdn.com/pk.svg
)
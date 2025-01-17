package com.example.taifex.data

import com.google.gson.annotations.SerializedName

data class BwibbuItem(
    @SerializedName("Code")
    val code: String,
    @SerializedName("DividendYield")
    val dividendYield: String,
    @SerializedName("Name")
    val name: String,
    @SerializedName("PBratio")
    val pbRatio: String,
    @SerializedName("PEratio")
    val peRatio: String
)
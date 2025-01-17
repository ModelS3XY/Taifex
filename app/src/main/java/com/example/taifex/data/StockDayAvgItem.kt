package com.example.taifex.data

import com.google.gson.annotations.SerializedName

data class StockDayAvgItem(
    @SerializedName("ClosingPrice")
    val closingPrice: String,
    @SerializedName("Code")
    val code: String,
    @SerializedName("MonthlyAveragePrice")
    val monthlyAveragePrice: String,
    @SerializedName("Name")
    val name: String
)
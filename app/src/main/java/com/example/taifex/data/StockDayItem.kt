package com.example.taifex.data

import com.google.gson.annotations.SerializedName

data class StockDayItem(
    @SerializedName("Change")
    val change: String,
    @SerializedName("ClosingPrice")
    val closingPrice: String,
    @SerializedName("Code")
    val code: String,
    @SerializedName("HighestPrice")
    val highestPrice: String,
    @SerializedName("LowestPrice")
    val lowestPrice: String,
    @SerializedName("Name")
    val name: String,
    @SerializedName("OpeningPrice")
    val openingPrice: String,
    @SerializedName("TradeValue")
    val tradeValue: String,
    @SerializedName("TradeVolume")
    val tradeVolume: String,
    @SerializedName("Transaction")
    val transaction: String
)
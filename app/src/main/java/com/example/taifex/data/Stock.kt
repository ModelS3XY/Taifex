package com.example.taifex.data

data class StockData(
    val code: String,
    val name: String,
    val peRatio: String? = null,
    val dividendYield: String? = null,
    val pbRatio: String? = null,
    val closingPrice: String? = null,
    val monthlyAveragePrice: String? = null,
    val tradeVolume: String? = null,
    val tradeValue: String? = null,
    val openingPrice: String? = null,
    val highestPrice: String? = null,
    val lowestPrice: String? = null,
    val change: String? = null,
    val transaction: String? = null,
)

val stocks = listOf(
    StockData(
        "0050",
        "元大台灣",
        "28.49",
        "2.95",
        "1.06",
        "192.35",
        "193.30",
        "8215118",
        "1589824232",
        "194.70",
        "194.75",
        "192.25",
        "-0.7000",
        "12809"
    ),
    StockData(
        "0050",
        "元大台灣",
        "28.49",
        "2.95",
        "1.06",
        "192.35",
        "193.30",
        "8215118",
        "1589824232",
        "194.70",
        "194.75",
        "192.25",
        "-0.7000",
        "12809"
    ),
    StockData(
        "0050",
        "元大台灣",
        "28.49",
        "2.95",
        "1.06",
        "192.35",
        "193.30",
        "8215118",
        "1589824232",
        "194.70",
        "194.75",
        "192.25",
        "-0.7000",
        "12809"
    ),
    StockData(
        "0050",
        "元大台灣",
        "28.49",
        "2.95",
        "1.06",
        "192.35",
        "193.30",
        "8215118",
        "1589824232",
        "194.70",
        "194.75",
        "192.25",
        "-0.7000",
        "12809"
    ),
    StockData(
        "0050",
        "元大台灣",
        "28.49",
        "2.95",
        "1.06",
        "192.35",
        "193.30",
        "8215118",
        "1589824232",
        "194.70",
        "194.75",
        "192.25",
        "-0.7000",
        "12809"
    )
)
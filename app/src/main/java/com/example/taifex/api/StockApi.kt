package com.example.taifex.api

import com.example.taifex.data.BwibbuItem
import com.example.taifex.data.StockDayAvgItem
import com.example.taifex.data.StockDayItem
import retrofit2.http.GET

interface StockApi {
    @GET("v1/exchangeReport/BWIBBU_ALL")
    suspend fun getPeData(): List<BwibbuItem>

    @GET("v1/exchangeReport/STOCK_DAY_AVG_ALL")
    suspend fun getAveragePriceData(): List<StockDayAvgItem>

    @GET("v1/exchangeReport/STOCK_DAY_ALL")
    suspend fun getTradeData(): List<StockDayItem>
}
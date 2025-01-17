package com.example.taifex.api

import com.example.taifex.data.StockData
import com.example.taifex.network.RetrofitInstance

class StockRepository {
    private val api: StockApi = RetrofitInstance.provideStockApi()

    suspend fun getCombinedStockData(): List<StockData> {
        // 並行請求三個api
        val peData = api.getPeData()
        val averagePriceData = api.getAveragePriceData()
        val tradeData = api.getTradeData()

        // 根據 Code組合
        return tradeData.map { trade ->
            val pe = peData.find { it.code == trade.code }
            val avgPrice = averagePriceData.find { it.code == trade.code }

            StockData(
                code = trade.code,
                name = trade.name,
                peRatio = pe?.peRatio,
                dividendYield = pe?.dividendYield,
                pbRatio = pe?.pbRatio,
                closingPrice = avgPrice?.closingPrice ?: trade.closingPrice,
                monthlyAveragePrice = avgPrice?.monthlyAveragePrice,
                tradeVolume = trade.tradeVolume,
                tradeValue = trade.tradeValue,
                openingPrice = trade.openingPrice,
                highestPrice = trade.highestPrice,
                lowestPrice = trade.lowestPrice,
                change = trade.change,
                transaction = trade.transaction
            )
        }
    }
}

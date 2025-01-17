package com.example.taifex.view

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.taifex.data.StockData
import com.example.taifex.ui.theme.TaifexTheme

@Composable
fun StockCard(stock: StockData, onClick: () -> Unit) {
    Column(modifier = Modifier.clickable(onClick=onClick).border(
        1.dp,
        Color.White.copy(0.5f),
        shape = RoundedCornerShape(30.dp)
    ).padding().clip(
        RoundedCornerShape(30.dp)
    ).background(Color.White.copy(0.2f))) {
        Column(modifier = Modifier.padding(8.dp, 8.dp))
        {
            Text(stock.code, modifier = Modifier.padding(top = 8.dp))
            Text(stock.name, fontSize = 20.sp, fontWeight = FontWeight.Bold)
            Column(modifier = Modifier.padding(start = 24.dp, top = 8.dp, end = 16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                    Text("開盤價")
                    Text(stock.openingPrice.toString(),
                        color = getOpeningPriceColor(stock),
                        fontWeight = FontWeight.SemiBold)
                    Text("收盤價")
                    Text(stock.closingPrice.toString(),
                        color = getClosingPriceColor(stock),
                        fontWeight = FontWeight.SemiBold
                    )
                }
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                    Text("最高價")
                    Text(stock.highestPrice.toString(),
                        fontWeight = FontWeight.SemiBold)
                    Text("最低價")
                    Text(stock.lowestPrice.toString(),
                        fontWeight = FontWeight.SemiBold)
                }
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                    Text("漲跌價差")
                    Text(stock.change.toString(),
                        color = getChangeColor(stock),
                        fontWeight = FontWeight.SemiBold
                    )
                    Text("月平均價")
                    Text(stock.monthlyAveragePrice.toString(),
                        fontWeight = FontWeight.SemiBold)
                }
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                    Text("成交筆數")
                    Text(stock.transaction.toString(),
                        fontWeight = FontWeight.SemiBold)
                }
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                    Text("成交股數")
                    Text(stock.tradeVolume.toString(),
                        fontWeight = FontWeight.SemiBold)
                }
                Row(modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp), horizontalArrangement = Arrangement.SpaceBetween) {
                    Text("成交金額")
                    Text(stock.tradeValue.toString(),
                        fontWeight = FontWeight.SemiBold)
                }
            }
        }
    }
}

// 動態設定顏色的邏輯
fun getClosingPriceColor(stockData: StockData): Color {

    val closingPrice = stockData.closingPrice?.toFloatOrNull()
    val monthlyAveragePrice = stockData.monthlyAveragePrice?.toFloatOrNull()

    return if (closingPrice != null && monthlyAveragePrice != null) {
        if (closingPrice > monthlyAveragePrice) {
            Color.Red
        } else {
            Color.Green
        }
    } else {
        Color.Gray
    }
}

// 動態設定顏色的邏輯
fun getOpeningPriceColor(stockData: StockData): Color {

    val openingPrice = stockData.openingPrice?.toFloatOrNull()
    val monthlyAveragePrice = stockData.monthlyAveragePrice?.toFloatOrNull()

    return if (openingPrice != null && monthlyAveragePrice != null) {
        if (openingPrice > monthlyAveragePrice) {
            Color.Red
        } else {
            Color.Green
        }
    } else {
        Color.Gray
    }
}

fun getChangeColor(stockData: StockData): Color {
    val change = stockData.change?.toFloatOrNull()

    return if (change != null) {
        if (change > 0) {
            Color.Red
        } else {
            Color.Green
        }
    } else {
        Color.Gray
    }
}

@Preview
@Composable
fun PreviewStockCard() {
    TaifexTheme {
        StockCard(
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
        ) {
//            selectedStock = stock
//            showDialog = true // 點擊後顯示 AlertDialog
        }
    }
}
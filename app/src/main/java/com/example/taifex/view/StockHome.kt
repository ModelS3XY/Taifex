package com.example.taifex.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.taifex.viewmodel.StockViewModel

@Composable
fun StockHome(paddingValues: PaddingValues, stockViewModel: StockViewModel) {
//    val stockRepository = StockRepository()
//    val stockViewModel: StockViewModel = viewModel(factory = StockViewModelFactory(stockRepository))
    val stockData = stockViewModel.sortStockData.collectAsState()
    val isLoading = stockViewModel.isLoading.collectAsState()
    val sortOrder = stockViewModel.sortOrder.collectAsState()

    LaunchedEffect(Unit) {
        stockViewModel.fetchStockData()
    }

    if (sortOrder.value == "asc") {
        stockData.value.sortedBy { it.code }
    } else {
        stockData.value.sortedByDescending { it.code }
    }

    LazyColumn(modifier = Modifier.padding(16.dp, 16.dp).padding(paddingValues), verticalArrangement = Arrangement.spacedBy(16.dp)) {
        items(stockData.value) { stock ->
            StockCard(stock, onClick = {
                stockViewModel.selectStockData(stock)
//                showDialog = true // 點擊後顯示 AlertDialog
            })
        }
    }

    if (isLoading.value) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black.copy(alpha = 0.3f))
        ) {
            CircularProgressIndicator(
                modifier = Modifier
                    .align(Alignment.Center)
            )
        }
    }
}


//@Preview
//@Composable
//fun PreviewStockHome() {
//    TaifexTheme {
//        StockHome(paddingValues = PaddingValues())
//    }
//}
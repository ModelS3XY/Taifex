package com.example.taifex.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.taifex.api.StockRepository
import com.example.taifex.ui.theme.TaifexTheme
import com.example.taifex.viewmodel.StockViewModel
import com.example.taifex.viewmodel.StockViewModelFactory

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen() {
    val stockRepository = StockRepository()
    val stockViewModel: StockViewModel = viewModel(factory = StockViewModelFactory(stockRepository))
    var showDialog by remember { mutableStateOf(false) }
    var sortOrder by remember { mutableStateOf("desc") }
    val selectStock = stockViewModel.selectStockData.collectAsState()

    if (showDialog) {
        ModalBottomSheet(
            onDismissRequest = { showDialog = false },
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                TextButton(onClick = {
                    sortOrder = "asc"
                    stockViewModel.setSortOrder(sortOrder)
                    showDialog = false
                }) {
                    Text("依股票代號升序")
                }
                TextButton(onClick = {
                    sortOrder = "desc"
                    stockViewModel.setSortOrder(sortOrder)
                    showDialog = false
                }) {
                    Text("依股票代號降序")
                }
            }
        }
    }


    if (selectStock.value != null) {
        AlertDialog(
            onDismissRequest = { showDialog = false },
            title = { Text("${selectStock.value!!.code} ${selectStock.value!!.name} ") },
            text = {
                Column {
                    Row {
                        Text("本益比")
                        Spacer(modifier = Modifier.weight(1f))
                        Text(selectStock.value!!.peRatio ?: "無資料")
                    }
                    Row {
                        Text("殖利率")
                        Spacer(modifier = Modifier.weight(1f))
                        Text(if(selectStock.value!!.dividendYield != null ) selectStock.value!!.dividendYield!! + "%" else "無資料" )
                    }
                    Row {
                        Text("股價淨值比")
                        Spacer(modifier = Modifier.weight(1f))
                        Text(selectStock.value!!.pbRatio ?: "無資料")
                    }
                }
            },
            confirmButton = {
                TextButton(onClick = { stockViewModel.selectStockData(null) }) {
                    Text("關閉")
                }
            }
        )
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text("",
                    )
                },
                colors = TopAppBarColors(
                    Color.Transparent,
                    Color.Transparent,
                    Color.Transparent,
                    Color.Transparent,
                    Color.Transparent,),
                actions = {
                    IconButton({showDialog = true}) {
                        Icon(Icons.Default.Menu,"Menu", tint = Color.White)
                    }
                },
            )
        },
        containerColor = Color(33,17,52),
    ) { padding ->
        StockHome(padding, stockViewModel)
    }
}

@Preview
@Composable
fun PreviewHomeScreen() {
    TaifexTheme {
        HomeScreen()
    }
}
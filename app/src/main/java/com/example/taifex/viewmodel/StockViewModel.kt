package com.example.taifex.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.taifex.api.StockRepository
import com.example.taifex.data.StockData
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class StockViewModel(private val repository: StockRepository) : ViewModel() {
//    private val _stockData = MutableStateFlow<List<StockData>>(emptyList())
//    val stockData: StateFlow<List<StockData>> = _stockData

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _sortOrder = MutableStateFlow("desc")
    val sortOrder: StateFlow<String> = _sortOrder

    private var _sortStockData = MutableStateFlow<List<StockData>>(emptyList())
    var sortStockData: StateFlow<List<StockData>> = _sortStockData

    private var _selectStockData = MutableStateFlow<StockData?>(null)
    var selectStockData: StateFlow<StockData?> = _selectStockData

    fun fetchStockData() {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val data = repository.getCombinedStockData()
                _sortStockData.value = data.sortedByDescending { it.code }
//                _stockData.value = data
            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun setSortOrder(sort: String) {
        _sortOrder.value = sort
        if (sort == "asc") {
            _sortStockData.value = _sortStockData.value.sortedBy { it.code }
        } else {
            _sortStockData.value = _sortStockData.value.sortedByDescending { it.code }
        }
    }

    fun selectStockData(stock: StockData?) {
        _selectStockData.value = stock
    }
}

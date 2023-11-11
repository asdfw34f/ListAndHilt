package com.example.listandhilt.Data.Helpers

import com.example.listandhilt.Data.Rates
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

class RatesSorter @Inject constructor(){
    fun sortRateName(rates:StateFlow<Rates>) {
        rates.value.rates.sortBy { it.name }
    }

    fun sortRateBroadCast(rates:StateFlow<Rates>) {
        rates.value.rates.sortBy { it.type }
    }

    fun sortRateAccess(rates:StateFlow<Rates>) {
        rates.value.rates.sortBy { it.access }
    }
}
package com.example.listandhilt.Data.Helpers

import com.example.listandhilt.Data.Rates
import javax.inject.Inject

class RatesSorter @Inject constructor(){
    fun sortRateName(rates: Rates) {
        rates.rates.value.sortBy { it.name }
    }

    fun sortRateBroadCast(rates: Rates) {
        rates.rates.value.sortBy { it.type }
    }

    fun sortRateAccess(rates: Rates) {
        rates.rates.value.sortBy { it.access }
    }
}
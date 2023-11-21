package com.example.listandhilt.Data.Helpers

import com.example.listandhilt.Data.Rate
import com.example.listandhilt.Data.Rates
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

class RateAdder @Inject constructor() {
    fun addRate(rates: Rates, rate: Rate) {
        rates.rates.value.add(rate)
    }
}
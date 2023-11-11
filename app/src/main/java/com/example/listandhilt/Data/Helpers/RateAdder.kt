package com.example.listandhilt.Data.Helpers

import com.example.listandhilt.Data.Rate
import com.example.listandhilt.Data.Rates
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

class RateAdder @Inject constructor() {
    fun addRate(rates: StateFlow<Rates>, rate: Rate) {
        rates.value.rates.add(rate)
    }
}
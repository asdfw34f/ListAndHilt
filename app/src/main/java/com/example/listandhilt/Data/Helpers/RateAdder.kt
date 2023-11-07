package com.example.listandhilt.Data.Helpers

import com.example.listandhilt.Data.Rate
import com.example.listandhilt.Data.Rates

class RateAdder {
    fun addRate(rates: Rates, rate: Rate): Rates {
        rates.rates.add(rate)
        return rates
    }
}
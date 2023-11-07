package com.example.listandhilt.Data.Helpers

import com.example.listandhilt.Data.Rates

class RateRemover {
    fun removeRate(rates: Rates, name:String): Rates {
        rates.rates.removeIf { it.name == name }
        return rates
    }
}
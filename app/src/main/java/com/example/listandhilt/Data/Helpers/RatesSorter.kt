package com.example.listandhilt.Data.Helpers

import com.example.listandhilt.Data.Rates

class RatesSorter {
    fun sortRateName(rates: Rates): Rates {
        rates.rates.sortBy { it.name }
        return rates
    }

    fun sortRateBroadCast(rates: Rates): Rates {
        rates.rates.sortBy { it.type }
        return rates
    }

    fun sortRateAccess(rates: Rates): Rates {
        rates.rates.sortBy { it.access }
        return rates
    }
}
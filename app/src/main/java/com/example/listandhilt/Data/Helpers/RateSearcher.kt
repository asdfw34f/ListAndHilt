package com.example.listandhilt.Data.Helpers

import com.example.listandhilt.Data.Rate
import com.example.listandhilt.Data.Rates
import com.example.listandhilt.Data.Types.BroadCast

class RateSearcher {
    fun searchRateName(rates: Rates, par: String): Rates {
        return Rates(rates.rates.filter { it.name == par }.toMutableList())
    }
    fun searchRateIndex(rates: Rates, par:Int): Rates?{
        val res:MutableList<Rate> = mutableListOf()

        try {
            res.add(rates.rates[par])
        }catch (i:IndexOutOfBoundsException){
            return null
        }
        return if (res.isNotEmpty())
            Rates(res)
        else null

    }
    fun searchRateAccess(rates: Rates, par:Boolean): Rates {
        return Rates(rates.rates.filter { it.access == par }.toMutableList())

    }
    fun searchRateBradCast(rates: Rates, par: BroadCast): Rates {
        return Rates(rates.rates.filter { it.type == par }.toMutableList())
    }
}
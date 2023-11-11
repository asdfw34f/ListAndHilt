package com.example.listandhilt.Data.Helpers

import com.example.listandhilt.Data.Rate
import com.example.listandhilt.Data.Rates
import com.example.listandhilt.Data.Types.BroadCast
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

class RateSearcher @Inject constructor(){
    fun searchRateName(rates: StateFlow<Rates>, par: String): Rates {
        return Rates((rates.value.rates.filter { it.name == par }).toMutableList())
    }
    fun searchRateIndex(rates: StateFlow<Rates>, par:Int): Rates?{
        val res:MutableList<Rate> = mutableListOf()

        try {
            res.add(rates.value.rates[par])
        }catch (i:IndexOutOfBoundsException){
            return null
        }
        return if (res.isNotEmpty())
            Rates(res)
        else null

    }
    fun searchRateAccess(rates: StateFlow<Rates>, par:Boolean): Rates {
        return Rates(rates.value.rates.filter { it.access == par }.toMutableList())

    }
    fun searchRateBradCast(rates: StateFlow<Rates>, par: BroadCast): Rates {
        return Rates(rates.value.rates.filter { it.type == par }.toMutableList())
    }
}
package com.example.listandhilt.Data.Helpers

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.toMutableStateList
import com.example.listandhilt.Data.Rate
import com.example.listandhilt.Data.Rates
import com.example.listandhilt.Data.Types.BroadCast
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

class RateSearcher @Inject constructor(){
    fun searchRateName(rates: Rates, par: String): Rates {
        return Rates(MutableStateFlow(rates.rates.value.filter { it.name == par }.toMutableList()))
    }
    fun searchRateAccess(rates: Rates, par:Boolean): Rates {
        return Rates(MutableStateFlow(rates.rates.value.filter { it.access == par }.toMutableList()))

    }
    fun searchRateBradCast(rates: Rates, par: BroadCast): Rates {
        return Rates(MutableStateFlow(rates.rates.value.filter { it.type == par }.toMutableList()))
    }
}
package com.example.listandhilt.Data.Helpers

import com.example.listandhilt.Data.Rates
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

class RateRemover @Inject constructor(){
    fun removeRate(rates: StateFlow<Rates>, name:String) {
        rates.value.rates.removeIf { it.name == name }
    }
}
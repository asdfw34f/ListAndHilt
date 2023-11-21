package com.example.listandhilt.Data.Helpers

import com.example.listandhilt.Data.Rates
import javax.inject.Inject

class RateRemover @Inject constructor(){
    fun removeRate(rates: Rates, name:String) {
        rates.rates.value.removeIf { it.name == name }
    }
}
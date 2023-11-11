package com.example.listandhilt.Data.Helpers

import com.example.listandhilt.Data.Rates
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

class RatesPrinter @Inject constructor(){
    fun printRates(rates: StateFlow<Rates>):String{
        var resultString = "| Name\t| Type of BroadCast\t| Access|\n"
        for (rate in rates.value.rates) {
            resultString += "${rate.name}\t${rate.type}\t${if (rate.access) "Yes" else "No"}\n"
        }
        return if( resultString == "| Name\t| Type of BroadCast\t| Access|\n") "the list is empty"
        else resultString
    }
}
package com.example.listandhilt.Data.Helpers

import com.example.listandhilt.Data.Rates
import com.example.listandhilt.Data.Types.BroadCast
import javax.inject.Inject

class RateEditor @Inject constructor(){
    fun editRateName(rates: Rates, i: Int, name: String) {
         try {
             rates.rates.value[i].name = name

        } catch (_: IndexOutOfBoundsException) {

        }
    }

    fun editRateType(rates: Rates, i: Int, newType: BroadCast) {
        try {
            rates.rates.value[i].type = newType
        } catch (_: IndexOutOfBoundsException) {

        }
    }

    fun editRateAccess(rates: Rates, i: Int, newAccess: Boolean) {
         try {
             rates.rates.value[i].access = newAccess

        } catch (_: IndexOutOfBoundsException) {

        }
    }
}
package com.example.listandhilt.Data.Helpers

import com.example.listandhilt.Data.Rates
import com.example.listandhilt.Data.Types.BroadCast
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

class RateEditor @Inject constructor(){
    fun editRateName(rates:  StateFlow<Rates>, i: Int, name: String) {
         try {
            rates.value.rates[i].name = name

        } catch (_: IndexOutOfBoundsException) {

        }
    }

    fun editRateType(rates: StateFlow<Rates>, i: Int, newType: BroadCast) {
        try {
            rates.value.rates[i].type = newType
        } catch (_: IndexOutOfBoundsException) {

        }
    }

    fun editRateAccess(rates:  StateFlow<Rates>, i: Int, newAccess: Boolean) {
         try {
            rates.value.rates[i].access = newAccess

        } catch (_: IndexOutOfBoundsException) {

        }
    }
}
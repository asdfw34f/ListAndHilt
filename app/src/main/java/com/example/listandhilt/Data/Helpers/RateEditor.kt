package com.example.listandhilt.Data.Helpers

import com.example.listandhilt.Data.Rates
import com.example.listandhilt.Data.Types.BroadCast

class RateEditor {
    fun editRateName(rates: Rates, i: Int, name: String): Rates? {
        return try {
            rates.rates[i].name = name
            rates
        } catch (e: IndexOutOfBoundsException) {
            null
        }
    }

    fun editRateType(rates: Rates, i: Int, newType: BroadCast): Rates? {
        return try {
            rates.rates[i].type = newType
            rates
        } catch (e: IndexOutOfBoundsException) {
            null
        }
    }

    fun editRateAccess(rates: Rates, i: Int, newAccess: Boolean): Rates? {
        return try {
            rates.rates[i].access = newAccess
            rates
        } catch (e: IndexOutOfBoundsException) {
            null
        }
    }
}
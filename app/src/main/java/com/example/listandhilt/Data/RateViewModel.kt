package com.example.listandhilt.Data

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.listandhilt.Data.Helpers.RateAdder
import com.example.listandhilt.Data.Helpers.RateEditor
import com.example.listandhilt.Data.Helpers.RateRemover
import com.example.listandhilt.Data.Helpers.RateSearcher
import com.example.listandhilt.Data.Helpers.RatesPrinter
import com.example.listandhilt.Data.Helpers.RatesSorter
import com.example.listandhilt.Data.Types.BroadCast
import com.example.listandhilt.Data.Types.TypeEdit
import com.example.listandhilt.Data.Types.TypeSearch
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel

class RateViewModel constructor(
    private var editor: RateEditor = RateEditor(),
    private var adder: RateAdder = RateAdder(),
    private var remover: RateRemover = RateRemover(),
    private var sorter: RatesSorter = RatesSorter(),
    private var searcher: RateSearcher = RateSearcher(),
    private var printer: RatesPrinter = RatesPrinter(),
    private val coroutineScope: CoroutineScope = CoroutineScope(SupervisorJob() + Dispatchers.Main.immediate)
): ViewModel()  {


    fun print(rates: Rates):String {
        return printer.printRates(rates)
    }
     fun search(rates: Rates, search: TypeSearch, name:String, index: Int, type: BroadCast, access: Boolean): Rates {


         return when (search) {
             TypeSearch.Name -> {
                 if (name.isNotBlank() && name != "")
                     searcher.searchRateName(rates, name)
                 else null
             }

             TypeSearch.Access -> {
                 searcher.searchRateAccess(rates, access)
             }

             TypeSearch.BroadCast -> {
                 searcher.searchRateBradCast(rates, type)
             }

             TypeSearch.Index -> {

                 searcher.searchRateIndex(rates, index)
             }
         } ?: // println("Rate wasn't fund")
         rates
     }
     fun sort(rates: Rates, sort: TypeEdit): Rates {
        val res = when (sort){
            TypeEdit.Name -> {
                sorter.sortRateName(rates)
            }
            TypeEdit.Access -> {
                sorter.sortRateAccess(rates)
            }
            TypeEdit.BroadCast -> {
                sorter.sortRateBroadCast(rates)

            }
        }
        return res
    }
     fun edit(rates: Rates, index:Int, edit: TypeEdit, newName:String, newBroadCast: BroadCast, newAccess:Boolean): Rates {
         return when (edit) {
             TypeEdit.BroadCast -> {
                 editor.editRateType(rates, index, newBroadCast)
             }

             TypeEdit.Access -> {
                 editor.editRateAccess(rates, index, newAccess)
             }

             TypeEdit.Name -> {
                 if (newName != "" && newName.isNotBlank())
                     editor.editRateName(rates, index, newName)
                 else null
             }
         } ?: //        println("Rate wasn't edit")
         rates
     }
     fun add(rates: Rates, name:String, type: BroadCast, access:Boolean): Rates {
        if (name != "" && name.isNotBlank()) {
            return adder.addRate(rates, Rate(name, type, access))
        }
    //    println("Rate wasn't add")
        return rates

    }
     fun remove(rates: Rates, name: String): Rates {
        if (name != "" && name.isNotBlank()) {
            return remover.removeRate(rates, name)
        }
   //     println("Rate wasn't remove")
        return rates
    }

    override fun onCleared() {
        coroutineScope.cancel()
    }

}
package com.example.listandhilt.Data

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
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class RateViewModel @Inject constructor(
): ViewModel() {


    val rates: MutableStateFlow<Rates> = MutableStateFlow(
        Rates(
            mutableListOf(
                Rate(
                    "MTS RUS",
                    BroadCast.HD,
                    false
                ),
                Rate(
                    "BEELINE",
                    BroadCast.HD,
                    true
                )
            )
        )
    )

    @Inject
    lateinit var editor: RateEditor

    @Inject
    lateinit var adder: RateAdder

    @Inject
    lateinit var remover: RateRemover

    @Inject
    lateinit var sorter: RatesSorter

    @Inject
    lateinit var searcher: RateSearcher

    @Inject
    lateinit var printer: RatesPrinter

    fun print(): String {
        return printer.printRates(rates.asStateFlow())
    }

    fun search(search: TypeSearch, name: String, index: Int, type: BroadCast, access: Boolean) {
        when (search) {
            TypeSearch.Name -> {
                if (name.isNotBlank() && name != "")
                    searcher.searchRateName(rates, name)
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
        }  // println("Rate wasn't fund")

    }

    fun sort(sort: TypeEdit) {
        when (sort) {
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
    }

    fun edit(
        index: Int,
        edit: TypeEdit,
        newName: String,
        newBroadCast: BroadCast,
        newAccess: Boolean
    ) {
        when (edit) {
            TypeEdit.BroadCast -> {
                editor.editRateType(rates, index, newBroadCast)
            }

            TypeEdit.Access -> {
                editor.editRateAccess(rates, index, newAccess)
            }

            TypeEdit.Name -> {
                if (newName != "" && newName.isNotBlank())
                    editor.editRateName(rates, index, newName)

            }
        }
    }

    fun add(name: String, type: BroadCast, access: Boolean) {
        if (name != "" && name.isNotBlank()) {
            adder.addRate(rates, Rate(name, type, access))
        }
    }

    fun remove(name: String) {
        if (name != "" && name.isNotBlank()) {
            remover.removeRate(rates, name)
        }
    }
}
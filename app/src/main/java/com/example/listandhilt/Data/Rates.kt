package com.example.listandhilt.Data

import kotlinx.coroutines.flow.MutableStateFlow

data class Rates(
    var rates:MutableStateFlow<MutableList<Rate>>
)
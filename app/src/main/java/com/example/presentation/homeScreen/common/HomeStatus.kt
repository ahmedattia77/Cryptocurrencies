package com.example.presentation.homeScreen.common

import com.example.koin.data.JON.currencyResponse.Data

data class HomeStatus(
    val data: Data ?= null,
    val loading: Boolean = false,
    val error: String ?= null
)
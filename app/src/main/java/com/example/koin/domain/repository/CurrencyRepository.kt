package com.example.koin.domain.repository

import com.example.koin.data.ApiStatus
import com.example.koin.data.JON.currencyResponse.CurrencyResponse

interface CurrencyRepository {
    suspend fun getCurrency() : ApiStatus<CurrencyResponse>
}
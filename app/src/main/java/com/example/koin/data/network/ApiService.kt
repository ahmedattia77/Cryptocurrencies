package com.example.koin.data.network

import com.example.koin.data.JON.currencyResponse.CurrencyResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("listing?start=1&limit=100&convert=USD")
    suspend fun getCurrency() : CurrencyResponse
}
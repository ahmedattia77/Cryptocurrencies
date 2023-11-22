package com.example.koin.data.repository

import com.example.koin.data.ApiStatus
import com.example.koin.data.JON.currencyResponse.CurrencyResponse
import com.example.koin.data.network.ApiService
import com.example.koin.domain.repository.CurrencyRepository
import retrofit2.HttpException


class CurrencyRepositoryImp(
    private val apiService: ApiService
) : CurrencyRepository {

    override suspend fun getCurrency(): ApiStatus<CurrencyResponse> {

        try {
            apiService.getCurrency().also {
                return ApiStatus.Success(it)
            }
        } catch (e: Exception) {
            return ApiStatus.Error(e.message.toString())
        } catch (e: HttpException) {
            return ApiStatus.Error(e.message.toString())
        }
    }
}
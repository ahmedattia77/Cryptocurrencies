package com.example.koin

import android.app.Application
import com.example.koin.data.network.ApiService
import com.example.koin.data.repository.CurrencyRepositoryImp
import com.example.koin.domain.repository.CurrencyRepository
import org.koin.core.context.startKoin
import org.koin.dsl.bind
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class App: Application(){

    override fun onCreate() {
        super.onCreate()

        startKoin {
            modules(module {
                single {
                    Retrofit
                        .Builder()
                        .baseUrl("https://api.coinmarketcap.com/data-api/v3/cryptocurrency/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build()
                }

                single {
                    val retrofit: Retrofit = get()
                    retrofit.create(ApiService::class.java)
                }

                single {
                    val api:ApiService = get()
                    CurrencyRepositoryImp(api)
                } bind CurrencyRepository::class
            })
        }

    }
}
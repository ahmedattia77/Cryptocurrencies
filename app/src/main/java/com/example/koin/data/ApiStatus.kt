package com.example.koin.data

sealed class ApiStatus<out T> {
    data class Success<T>(val data:T) : ApiStatus<T>()
    data class Error(val error: String) : ApiStatus<Nothing>()
    object Loading: ApiStatus<Nothing>()
}
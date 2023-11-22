package com.example.presentation.homeScreen

import androidx.compose.runtime.State
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.koin.data.ApiStatus
import com.example.koin.data.JON.currencyResponse.CurrencyResponse
import com.example.koin.domain.repository.CurrencyRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class HomeViewModel : ViewModel(), KoinComponent {
    private val currencyRepo: CurrencyRepository by inject()

    private val _state: MutableStateFlow<ApiStatus<CurrencyResponse>?>
                = MutableStateFlow(null)

    val state = _state.asStateFlow()

    init {
        getCurrency()
    }

    private fun getCurrency() {
        viewModelScope.launch {
            _state.update { ApiStatus.Loading}
            currencyRepo.getCurrency().also { res ->
                _state.update { res }
            }
        }
    }
}
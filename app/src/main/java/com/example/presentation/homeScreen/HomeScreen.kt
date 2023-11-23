package com.example.presentation.homeScreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.koin.data.ApiStatus
import com.example.presentation.homeScreen.common.singleItem

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = viewModel()
) {

    val data by viewModel.state.collectAsState()


    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start
    ) {

        when (val state = data) {
            is ApiStatus.Loading -> {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = "Loading")
                }
            }

            is ApiStatus.Success -> {
                Text(
                    modifier = Modifier.padding(top = 18.dp , start = 14.dp),
                    text = "CryptoCurrency24" ,
                    fontSize = 22.sp ,
                    fontWeight = FontWeight.Bold
                )
                LazyRow(
                    contentPadding = PaddingValues(14.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    items(state.data.data.cryptoCurrencyList.sortedBy { it.quotes.first().percentChange24h }.reversed()) {
                        singleItem(it)
                    }
                }
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    modifier = Modifier.padding(top = 18.dp , start = 14.dp),
                    text = "Low Shares" ,
                    fontSize = 22.sp ,
                    fontWeight = FontWeight.Bold
                )
                LazyRow(
                    contentPadding = PaddingValues(14.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    items(state.data.data.cryptoCurrencyList.sortedBy { it.quotes.first().percentChange24h }) {
                        singleItem(it)
                    }
                }
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    modifier = Modifier.padding(top = 18.dp , start = 14.dp),
                    text = "24 Volume Currencies" ,
                    fontSize = 22.sp ,
                    fontWeight = FontWeight.Bold
                )
                LazyRow(
                    contentPadding = PaddingValues(14.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    items(state.data.data.cryptoCurrencyList.sortedBy { it.quotes.first().volume24h }) {
                        singleItem(it)
                    }
                }
                Spacer(Modifier.height(4.dp))
                Text(
                    modifier = Modifier.padding(top = 18.dp , start = 14.dp),
                    text = "Top Currencies" ,
                    fontSize = 22.sp ,
                    fontWeight = FontWeight.Bold
                )
                LazyColumn(
                    modifier = Modifier.padding(horizontal = 14.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)){
                    items(state.data.data.cryptoCurrencyList){
                        singleItem(crypto = it)
                    }
                }
            }

            is ApiStatus.Error -> {}
            else -> {}
        }


    }
}
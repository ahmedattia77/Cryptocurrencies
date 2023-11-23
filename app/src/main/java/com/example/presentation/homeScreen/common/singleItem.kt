package com.example.presentation.homeScreen.common
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Green
import androidx.compose.ui.graphics.Color.Companion.Red
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.koin.data.JON.currencyResponse.CryptoCurrency
import kotlin.math.roundToInt
@Composable
fun singleItem(crypto: CryptoCurrency) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(80.dp)
            .shadow(8.dp)
            .clip(
                RoundedCornerShape(8.dp)
            )
            .background(MaterialTheme.colorScheme.primary)
            .clickable {}
            .padding(horizontal = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            Box(
                modifier = Modifier
                    .size(55.dp)
                    .clip(RoundedCornerShape(18.dp))
                    .background(MaterialTheme.colorScheme.secondary)
            ) {
                AsyncImage(
                    modifier = Modifier.size(4.dp),
                    model = ImageRequest
                        .Builder(LocalContext.current)
                        .data("https://s2.coinmarketcap.com/static/img/coins/64*64/${crypto.id}.png")
                        .crossfade(true)
                        .build(),
                    contentDescription = null
                )

            }
            Column {
                Text(text = crypto.name, fontSize = 14.sp, fontWeight = FontWeight.Bold)
                Text(text = crypto.symbol, fontSize = 14.sp, color = Color.Gray)
            }

            Column(horizontalAlignment = Alignment.End) {
                Text(text = "${((crypto.quotes.first().price * 100).roundToInt()) / 100.0}$")
                Spacer(modifier = Modifier.height(2.dp))
                val percent = ((crypto.quotes.first().percentChange24h * 100).roundToInt()) / 100
                val textColor = if (percent > 0) Green else Red
                Text(text = "${percent}%", color = textColor)

            }
        }
    }
}
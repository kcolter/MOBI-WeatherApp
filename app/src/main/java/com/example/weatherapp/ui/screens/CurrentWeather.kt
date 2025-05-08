package com.example.weatherapp.ui.screens

import com.example.weatherapp.R
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@Preview
@Composable
fun CurrentWeatherUI(){
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(state = rememberScrollState())
            .background(color = MaterialTheme.colorScheme.background)
            .padding(all = 20.dp)
            .fillMaxWidth()
            .border(3.dp, MaterialTheme.colorScheme.secondary , RectangleShape)
    )
    {
        //Weather Image
        Image(
            painter = painterResource(R.drawable.rainy),
            contentDescription = "Weather Image [Rainy]",
            modifier = Modifier
                .size(100.dp)
                .padding(top = 20.dp)
        )

        Spacer(modifier = Modifier.height(20.dp))

        //Condition and Temp on same row
        Row {
            //Condition
            Text(
                text = "Cloudy with Showers",
                style = MaterialTheme.typography.titleMedium
            )

            //Temp.
            Text(
                text = "10° celsius",
                style = MaterialTheme.typography.titleMedium
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        //Precip. type and amount on same row
        Row {
            Text(
                text = "Rain",
                style = MaterialTheme.typography.titleMedium
            )
            Spacer(modifier = Modifier.width(10.dp))
            Text(
                text = "~10mm",
                style = MaterialTheme.typography.titleMedium
            )
        }

        Spacer(modifier = Modifier.height(5.dp))

        ///wind direction and speed on same row
        Row {
            Text(
                text = "21 km/h",
                style = MaterialTheme.typography.titleMedium
            )
            Spacer(modifier = Modifier.width(10.dp))
            Text(
                text = "S/SW Wind",
                style = MaterialTheme.typography.titleMedium
            )
        }
    }
}
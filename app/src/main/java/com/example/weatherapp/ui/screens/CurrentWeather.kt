package com.example.weatherapp.ui.screens

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
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.weatherapp.MainViewModel

@Composable
fun CurrentWeatherUI(mainViewModel: MainViewModel) {

    val weather = mainViewModel.weather.collectAsState().value

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(state = rememberScrollState())
            .background(color = MaterialTheme.colorScheme.background)
            .padding(all = 20.dp)
            .fillMaxWidth()
            .border(3.dp, MaterialTheme.colorScheme.secondary, RectangleShape)
    )
    {
        //Weather Image
        Image(
            painter = rememberAsyncImagePainter("https:" + weather?.current?.condition?.icon),
            contentDescription = "" + weather?.current?.condition?.icon,
            modifier = Modifier
                .size(100.dp)
                .padding(top = 20.dp)
        )
        Spacer(modifier = Modifier.height(20.dp))

        //Condition
        Text(
            text = weather?.current?.condition?.text.toString(),
            style = MaterialTheme.typography.titleLarge
        )
        Spacer(modifier = Modifier.height(20.dp))

        //Temp.
        Text(
            text = weather?.current?.temperature.toString() + "° celsius",
            style = MaterialTheme.typography.titleLarge
        )
        Spacer(modifier = Modifier.height(20.dp))

        //Precipitation type and amount on same row
        if (weather?.current?.precipAmount?.toInt() != 0) { //if precipitation amount is 0, don't display this row
            Row {
                Text(
                    text = weather?.current?.precipAmount.toString() + "mm",
                    style = MaterialTheme.typography.titleLarge
                )
            }
            Spacer(modifier = Modifier.height(20.dp))
        }

        ///Wind direction and speed on same row
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = "Wind",
                style = MaterialTheme.typography.titleLarge
            )

            Row {
                Text(
                    text = weather?.current?.windDirection.toString(),
                    style = MaterialTheme.typography.titleLarge
                )
                Spacer(modifier = Modifier.width(10.dp))
                Text(
                    text = weather?.current?.windSpeed.toString() + " km/h",
                    style = MaterialTheme.typography.titleLarge
                )
            }
        }
    }
}
package com.example.weatherapp.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.weatherapp.MainViewModel
import com.example.weatherapp.R
import com.example.weatherapp.models.Forecast
import com.example.weatherapp.models.*

@Composable
fun ForecastedDay(fd: Forecastday){
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .background(color = MaterialTheme.colorScheme.background)
            .padding(all = 10.dp)
            .border(3.dp, MaterialTheme.colorScheme.secondary , RectangleShape)
    ) {
        Spacer(modifier = Modifier.height(10.dp))
        //date
        Text(
            text = fd.date,
            color = MaterialTheme.colorScheme.primary,
            style = MaterialTheme.typography.titleLarge
        )
        Spacer(modifier = Modifier.height(10.dp))

        //Weather image
        Image(
            painter = rememberAsyncImagePainter("https:" + fd.day.condition.icon),
            contentDescription = "Weather Image [placeholder]",
            modifier = Modifier
                .size(100.dp)
        )
        Spacer(modifier = Modifier.height(10.dp))

        //Condition
        Text(
            text = fd.day.condition.text,
            style = MaterialTheme.typography.titleMedium
        )
        Spacer(modifier = Modifier.height(20.dp))

        //Temperature
        Column (
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Temperature",
                style = MaterialTheme.typography.titleMedium
            )
            Row {
                Text(text = "High: " + fd.day.temperatureHigh + "° celsius")
                Spacer(modifier = Modifier.width(10.dp))
                Text(text = "Low: " + fd.day.temperatureLow + "° celsius")
            }
        }
        Spacer(modifier = Modifier.height(10.dp))

        //Precipitation
        Column (horizontalAlignment = Alignment.CenterHorizontally){
            Text(text = "Precipitation", style = MaterialTheme.typography.titleMedium)
            Row {
                Text(text = "Amount: " + fd.day.precipitationAmount + "mm") //TODO: once API is determined: rewrite so that the proper unit is given depending on precipitation type
                Spacer(modifier = Modifier.width(10.dp))
                Text(text = "Probability: " + fd.day.precipitationProbability + "%")
            }
        }
        Spacer(modifier = Modifier.height(10.dp))

        //Wind
        Column (horizontalAlignment = Alignment.CenterHorizontally){
            Text(text= "Wind", style = MaterialTheme.typography.titleMedium)
            Row {
                Text(text = "Speed: " + fd.day.windSpeed + " km/h")
            }
        }
        Spacer(modifier = Modifier.height(10.dp))

        //Humidity
        Text(
            text = "Humidity: " + fd.day.humidity + "%",
            modifier = Modifier
                .padding(bottom = 10.dp)
        )
    }
}

@Composable
fun MultiDayForecast(mainViewModel: MainViewModel){

    val weather = mainViewModel.weather.collectAsState().value

    LazyColumn (
        modifier = Modifier
            .padding(top = 20.dp)
    ){

        //created a ForecastedDay item from each forecast in the list IF weather is not null
        if (weather != null) {
            items(weather.forecast.forecastday) { fd ->
                ForecastedDay(fd)
            }
        }
    }
}
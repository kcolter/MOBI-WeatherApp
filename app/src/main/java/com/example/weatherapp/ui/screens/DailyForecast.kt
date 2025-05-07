package com.example.weatherapp.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.weatherapp.R


//rewrite data class once API/data-source and format is known
data class Forecast(val date: String, val imageRef: String, val tempHigh: Double, val tempLow: Double,
                    val condition: String, val precipType: String, val precipAmount: String, val precipProbability: Double,
                    val windDirection: String, val windSpeed: Double,
                    val humidity: Double)

object SampleForecasts {
    val day1 = Forecast("5/9/2025", "imageref", 15.0, 5.0, "Lots of rain", "Rain", "~5mm", 75.0, "South", 21.0, 20.0)
    val day2 = Forecast("5/10/2025", "imageref", 16.0, 6.0, "More rain", "Rain", "~3mm", 50.0, "East", 11.0, 30.0)
    val day3 = Forecast("5/11/2025", "imageref", 17.0, 7.0, "Yet more rain", "Rain", "~1mm", 25.0, "West", 15.0, 10.0)
}

@Composable
fun ForecastedDay(fc: Forecast ){
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .background(color = MaterialTheme.colorScheme.background)
            .padding(10.dp)
    ) {
        Spacer(modifier = Modifier.height(10.dp))
        //date
        Text(
            text = fc.date,
            color = MaterialTheme.colorScheme.primary,

        )

        Spacer(modifier = Modifier.height(10.dp))

        Image(
            painter = painterResource(R.drawable.rainy),
            contentDescription = "Weather Image [Rainy]",
            modifier = Modifier
                .size(100.dp)
        )
        Spacer(modifier = Modifier.height(10.dp))

        //Temperature
        Column {
            Text(
                text = "Temperature"
            )
            Row {
                Text(text = "High: " + fc.tempHigh)
                Text(text = "Low: " + fc.tempLow)
            }
        }
        Spacer(modifier = Modifier.height(10.dp))

        //Condition
        Text(
            text = fc.condition
        )
        Spacer(modifier = Modifier.height(10.dp))

        //precipitation
        Column (horizontalAlignment = Alignment.CenterHorizontally){
            Text(text = "Precipitation type: " + fc.precipType)
            Row {
                Text(text = "Amount: " + fc.precipAmount)
                Text(text = "Amount: " + fc.precipProbability)
            }
        }
        Spacer(modifier = Modifier.height(10.dp))

        //Wind
        Column {
            Text(text= "Wind")
            Row {
                Text(text = "Speed: " + fc.windSpeed)
                Text(text = "Direction: " + fc.windDirection)
            }
        }
        Spacer(modifier = Modifier.height(10.dp))

        //Humidity
        Text(
            text = "Humidity: " + fc.humidity
        )



    }
}

@Composable
fun ThreeDayForecast(){
    Column (
        modifier = Modifier
            .verticalScroll(state = rememberScrollState())
    ){
        ForecastedDay(SampleForecasts.day1)
        ForecastedDay(SampleForecasts.day2)
        ForecastedDay(SampleForecasts.day3)
    }
}
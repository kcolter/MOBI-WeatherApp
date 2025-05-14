package com.example.weatherapp

import androidx.lifecycle.ViewModel
import com.example.weatherapp.models.Current
import com.example.weatherapp.models.Forecast
import com.example.weatherapp.models.Weather
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class MainViewModel : ViewModel() {

    private val _weather = MutableStateFlow<Weather?>(null)
    val weather = _weather.asStateFlow()

    init {
        //create weather object
        val w = Weather(
            currentWeather = Current("Raining", 15.0, "Rain", 3.1, "North", 12.0, "imgpath"),
            forecasts = listOf(
                Forecast("May 15, 2025", 15.0, 5.1, "Sunny", "None", 0.0, 0.0, "North", 10.1, 10.0, "imgpath"),
                Forecast("May 16, 2025", 16.0, 6.1, "Sunny", "None", 0.0, 0.0, "North", 10.1, 10.0,"imgpath"),
                Forecast("May 17, 2025", 17.0, 7.1, "Sunny", "None", 0.0, 0.0, "North", 10.1, 10.0, "imgpath")
            )
        )
        _weather.value = w
    }
}
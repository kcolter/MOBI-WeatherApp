package com.example.weatherapp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.models.Current
import com.example.weatherapp.models.Forecast
import com.example.weatherapp.models.Weather
import com.example.weatherapp.services.WeatherService
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainViewModel : ViewModel() {


    var _longAndLat = ""

    private val _weather = MutableStateFlow<Weather?>(null)
    val weather = _weather.asStateFlow()

    //create retrofit instance
    val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl("https://api.weatherapi.com")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    //init interface
    val weatherService: WeatherService = retrofit.create(WeatherService::class.java)

    fun updateLocation(longAndLatIn: String){
        _longAndLat = longAndLatIn
        callAPI() //view models .launch located in here so we know Long. and Lat. are updated
    }

    fun callAPI(){
        if(_longAndLat.isBlank()) return

        //get data from API using updated longitude and latitude
        viewModelScope.launch {
            val w = weatherService.getForecast("d8e81af2b0f14539ba5143224252105", _longAndLat, 3, "no", "no") //retrieve from api

            _weather.value = w; //write value to _weather
        }
    }
}
package com.example.weatherapp.models

import com.google.gson.annotations.SerializedName

data class Current(
    val condition: Condition,
    @SerializedName("temp_c") val temperature: Double,
    val precipitationType: String, //no precip-type in api, will have to determine based on mm or in not being 0 IF precipitation is present
    val precip_mm: Double,
    val precip_in: Double,
    val wind_dir: String,
    val wind_kph: Double,
    //val imagePath: image will by taken from icon in condition
    )

//Condition  used in both Current and forecastday objects
data class Condition(
    val text: String,
    val icon: String, //the cdn-provided icon, 64x64 png
    val code: Int
)

data class Forecast(
    val forecastday: List<Forecastday> //api-response structure forecast->forecastday->0, 1, 2, ...
)

data class Forecastday(
    val date: String, // format of YYYY-MM-DD
    val day: Day,
    //val imagePath: String //image will by taken from icon in condition found in day
    )

data class Day(
    val condition: Condition,
    @SerializedName("maxtemp_c") val temperatureHigh: Double,
    @SerializedName("mintemp_c") val temperatureLow: Double,
    @SerializedName("maxwind_kph") val windSpeed: Double,
    @SerializedName("avghumidity")val humidity: Double,
    val precipitationType: String,
    @SerializedName("totalprecip_mm")val precipitationAmount: Double,
    @SerializedName("daily_chance_of_rain") val precipitationProbability: Double,
    val windDirection: String,
)

data class Weather(
    val currentWeather: Current,
    val forecast: Forecast //api-response structure forecast->forecastday->0, 1, 2, ...
)
package com.example.weatherapp.models

import com.google.gson.annotations.SerializedName

data class Current(
    val condition: Condition,
    @SerializedName("temp_c") val temperature: Double,
    @SerializedName("precip_mm") val precipAmount: Double,
    @SerializedName("wind_dir") val windDirection: String,
    @SerializedName("wind_kph") val windSpeed: Double
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
    )

data class Day(
    val condition: Condition,
    @SerializedName("maxtemp_c") val temperatureHigh: Double,
    @SerializedName("mintemp_c") val temperatureLow: Double,
    @SerializedName("maxwind_kph") val windSpeed: Double,
    @SerializedName("avghumidity")val humidity: Double,
    @SerializedName("totalprecip_mm")val precipitationAmount: Double,
    @SerializedName("daily_chance_of_rain") val precipitationProbability: Double,
    val windDirection: String
)

data class Location(
    val name: String,
    val region: String,
    val country: String
)

data class Weather(
    val current: Current,
    val forecast: Forecast, //api-response structure forecast->forecastday->0, 1, 2, etc.
    val location: Location
)
package com.example.weatherapp.models

data class Current(
    val condition: String,
    val temperature: Double,
    val precipitationType: String,
    val precipitationAmount: Double,
    val windDirection: String,
    val windSpeed: Double,
    val imagePath: String //potentially unneeded: unknown if a URI will be supplied by API, or if we're going to use local resources based on condition
    )

data class Forecast(
    val date: String,
    val temperatureHigh: Double,
    val temperatureLow: Double,
    val condition: String,
    val precipitationType: String,
    val precipitationAmount: Double,
    val precipitationProbability: Double,
    val windDirection: String,
    val windSpeed: Double,
    val humidity: Double,
    val imagePath: String //potentially unneeded: unknown if a URI will be supplied by API, or if we're going to use local resources based on condition
    )

data class Weather(
    val currentWeather: Current,
    val forecasts: List<Forecast>
)
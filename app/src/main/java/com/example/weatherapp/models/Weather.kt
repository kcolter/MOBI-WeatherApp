package com.example.weatherapp.models

data class Current(
    val condition: Condition,
    val temp_c: Double,
    val precipitationType: String, //no precip-type in api, will have to determine based on mm or in not being 0 IF precipitation is present
    val precip_mm: Double,
    val precip_in: Double,
    val wind_dir: String,
    val wind_kph: Double,
    //val imagePath: String image will by taken from icon in condition
    )


data class Condition(
    val text: String,
    val icon: String,
    val code: Int
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
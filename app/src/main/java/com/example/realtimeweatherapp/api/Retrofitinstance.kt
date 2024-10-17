package com.example.realtimeweatherapp.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object Retrofitinstance {
    private const val baesUrl = "https://api.weatherapi.com"

    private fun getInstance(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baesUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
val weatherApi : WeatherApi = getInstance().create(WeatherApi::class.java)
}
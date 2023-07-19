package com.example.vakifbank_case.repository

import com.example.vakifbank_case.model.WeatherResponse
import retrofit2.Call

interface WeatherRepository {
    fun getWeather(lat: Double, lon: Double, exclude: String, appid: String) : Call<WeatherResponse>
}
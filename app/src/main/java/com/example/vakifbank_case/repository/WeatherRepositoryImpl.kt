package com.example.vakifbank_case.repository

import com.example.vakifbank_case.model.WeatherResponse
import com.example.vakifbank_case.retrofit.ApiInterface
import retrofit2.Call

class WeatherRepositoryImpl(private val weatherApi: ApiInterface) : WeatherRepository {
    override fun getWeather(
        lat: Double,
        lon: Double,
        exclude: String,
        appid: String
    ): Call<WeatherResponse> {
        try {
            return weatherApi.getWeather(lat, lon, exclude, appid)
        } catch (e: Exception) {
            throw e
        }
    }

}
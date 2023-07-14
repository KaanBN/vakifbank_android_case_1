package com.example.vakifbank_case.retrofit

import com.example.vakifbank_case.model.WeatherResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {
    @GET("onecall")
    fun getWeather(
        @Query("lat") lat: Double,
        @Query("lon") lon: Double,
        @Query("exclude") exclude: String,
        @Query("appid") appid: String = "8ddadecc7ae4f56fee73b2b405a63659"
    ): Call<WeatherResponse>
}
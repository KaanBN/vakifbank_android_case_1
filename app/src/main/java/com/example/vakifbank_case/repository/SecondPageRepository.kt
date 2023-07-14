package com.example.vakifbank_case.repository

import androidx.lifecycle.MutableLiveData
import com.example.vakifbank_case.model.WeatherResponse
import com.example.vakifbank_case.retrofit.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SecondPageRepository {
    val weatherResponse = MutableLiveData<WeatherResponse>()

    fun getServicesApiCall(lat: Double, lon: Double, exclude: String) : MutableLiveData<WeatherResponse> {

        val call = RetrofitClient.apiInterface.getWeather(lat, lon, exclude)

        call.enqueue(object: Callback<WeatherResponse> {
            override fun onResponse(
                call: Call<WeatherResponse>,
                response: Response<WeatherResponse>
            ) {
                weatherResponse.value = response!!.body()
            }

            override fun onFailure(call: Call<WeatherResponse>, t: Throwable) {
                println("Error Happened")
            }

        })

        return weatherResponse
    }

}
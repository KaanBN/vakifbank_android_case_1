package com.example.vakifbank_case.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.vakifbank_case.model.WeatherResponse
import com.example.vakifbank_case.repository.SecondPageRepository
import com.example.vakifbank_case.retrofit.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SecondPageViewModel : ViewModel() {
    private var weatherLiveData = MutableLiveData<WeatherResponse>()

    fun getWeather(lat : Double, lon: Double, exclude: String){
        RetrofitClient.apiInterface.getWeather(lat, lon, exclude).enqueue(
            object : Callback<WeatherResponse>{

                override fun onResponse(
                    call: Call<WeatherResponse>,
                    response: Response<WeatherResponse>
                ) {
                    weatherLiveData.value = response.body()!!
                    return
                }

                override fun onFailure(call: Call<WeatherResponse>, t: Throwable) {
                    println("Error happened")
                }

            }
        )

    }

    fun observeWeatherLiveData() : LiveData<WeatherResponse> {
        return weatherLiveData;
    }
}
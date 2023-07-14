package com.example.vakifbank_case.viewmodel

import android.Manifest
import android.annotation.SuppressLint
import android.app.Application
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationManager
import androidx.core.app.ActivityCompat
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.vakifbank_case.model.WeatherResponse
import com.example.vakifbank_case.retrofit.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices


class SecondPageViewModel() : ViewModel() {
    private var weatherLiveData = MutableLiveData<WeatherResponse>()
    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient
    private val _currentLocation = MutableLiveData<Location>()
    val currentLocation : LiveData<Location> = _currentLocation
    fun init(application: Application){
        LocationServices.getFusedLocationProviderClient(application)
    }
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

    @SuppressLint("MissingPermission")
    fun getLocation(){
        fusedLocationProviderClient.lastLocation
            .addOnSuccessListener {
                location: Location? ->
                _currentLocation.value = location
            }
    }

    fun observeWeatherLiveData() : LiveData<WeatherResponse> {
        return weatherLiveData;
    }
}
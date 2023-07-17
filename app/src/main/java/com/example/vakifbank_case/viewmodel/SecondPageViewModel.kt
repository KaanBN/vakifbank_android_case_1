package com.example.vakifbank_case.viewmodel

import android.Manifest
import android.app.Application
import android.content.pm.PackageManager
import android.location.Location
import android.widget.ImageView
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.vakifbank_case.model.WeatherResponse
import com.example.vakifbank_case.retrofit.RetrofitClient
import com.example.vakifbank_case.view.SecondPage
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationServices
import com.google.android.gms.tasks.CancellationToken
import com.google.android.gms.tasks.CancellationTokenSource
import com.google.android.gms.tasks.OnTokenCanceledListener
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class SecondPageViewModel() : ViewModel() {
    private var weatherLiveData = MutableLiveData<WeatherResponse>()
    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient
    private val _currentLocation = MutableLiveData<Location>()

    private val permission = Manifest.permission.ACCESS_FINE_LOCATION
    private val requestCode = 1

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

    fun getLocation(application: SecondPage){

        if (ActivityCompat.checkSelfPermission(
                application,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                application,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                application,
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                101
            )

        }

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(application)
        fusedLocationProviderClient.getCurrentLocation(LocationRequest.PRIORITY_HIGH_ACCURACY, object : CancellationToken() {
            override fun onCanceledRequested(p0: OnTokenCanceledListener) = CancellationTokenSource().token

            override fun isCancellationRequested() = false
        }).addOnSuccessListener { location : android.location.Location? ->
            if (location != null) {
                _currentLocation.value = location
            }
        }

    }

    fun observeCurrentLocationLiveData() : LiveData<Location> {
        return currentLocation
    }

    fun observeWeatherLiveData() : LiveData<WeatherResponse> {
        return weatherLiveData;
    }

    fun loadImage(ImageView: ImageView, image: String){
        val url = "https://openweathermap.org/img/wn/$image.png"
        Picasso.get().load(url).into(ImageView)
    }
}
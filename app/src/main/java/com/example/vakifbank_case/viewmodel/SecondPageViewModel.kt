package com.example.vakifbank_case.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.vakifbank_case.model.WeatherResponse
import com.example.vakifbank_case.repository.SecondPageRepository

class SecondPageViewModel : ViewModel() {
    var weatherLiveData: MutableLiveData<WeatherResponse>? = null

    fun getUser() : LiveData<WeatherResponse>? {
        return weatherLiveData
    }
}
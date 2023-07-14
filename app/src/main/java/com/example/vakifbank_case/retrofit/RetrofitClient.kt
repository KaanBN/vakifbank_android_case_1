package com.example.vakifbank_case.retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    const val MainServer = "https://api.openweathermap.org/data/2.5/"
    val retrofitClient: Retrofit.Builder by lazy {
        Retrofit.Builder()
            .baseUrl(MainServer)
            .addConverterFactory(GsonConverterFactory.create())
    }

    val apiInterface: ApiInterface by lazy {
        retrofitClient.build().create(ApiInterface::class.java)
    }
}
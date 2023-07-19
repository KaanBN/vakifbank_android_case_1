package com.example.vakifbank_case.view

import android.content.Intent
import android.location.Geocoder
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.vakifbank_case.R
import com.example.vakifbank_case.adapter.WeatherAdapter
import com.example.vakifbank_case.databinding.ActivitySecondPage2Binding
import com.example.vakifbank_case.loadImage
import com.example.vakifbank_case.viewmodel.SecondPageViewModel


class SecondPage : AppCompatActivity() {

    private lateinit var binding: ActivitySecondPage2Binding
    lateinit var secondVievModel: SecondPageViewModel

    var intentApi: String = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        if(intent.extras!=null) {
            intentApi = intent.extras!!.getString("apikey")!!
        }

        super.onCreate(savedInstanceState)
        binding = ActivitySecondPage2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        binding.recyleView.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(this)

        secondVievModel = ViewModelProvider(this).get(SecondPageViewModel::class.java)
        secondVievModel.init(application)
    }

    override fun onResume() {
        super.onResume()
        secondVievModel.getLocation(this)
        secondVievModel.loading.observe( this, Observer{
            loading ->
            binding.loadingTextView.isVisible = loading
        }
        )

        secondVievModel.isError.observe(this, Observer{
            error->
            if (error){
                Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show()
            }
        })
        secondVievModel.observeCurrentLocationLiveData().observe(this, Observer {
            secondVievModel.getWeather(it.latitude, it.longitude, "hourly,minutely,alerts", intentApi)
            Geocoder(this).getFromLocation(it.latitude, it.longitude, 1).apply {
                binding.locationTextView.text = "${this!![0].adminArea}, ${this!![0].countryCode}"
            }
        })
        secondVievModel.observeWeatherLiveData().observe(this, Observer(){
            val currentDeg = it.current.temp - 273.15
            binding.dereceIdTextView.text = currentDeg.toInt().toString() + "°"
            val image = it.current.weather[0].icon
            binding.firstWeatherIcon.loadImage(image)
            binding.apply {
                binding.recyleView.adapter = WeatherAdapter(it)
            }
        }
        )
    }

    override fun onSupportNavigateUp(): Boolean {
        var intent = Intent(this@SecondPage, MainActivity::class.java)
        startActivity(intent)
        finish()
        return true
    }
}
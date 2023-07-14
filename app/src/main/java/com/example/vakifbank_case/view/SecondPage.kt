package com.example.vakifbank_case.view

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.navigation.ui.AppBarConfiguration
import com.example.vakifbank_case.databinding.ActivitySecondPage2Binding
import com.example.vakifbank_case.viewmodel.SecondPageViewModel

class SecondPage : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivitySecondPage2Binding
    lateinit var secondVievModel: SecondPageViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondPage2Binding.inflate(layoutInflater)
        setContentView(binding.root)
        secondVievModel = SecondPageViewModel()
        secondVievModel.getWeather(33.33,33.33, "hourly")
        val asd = secondVievModel.observeWeatherLiveData();
        asd.observe(
            this
        ) {
            println("it: $it")
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        var intent = Intent(this@SecondPage, MainActivity::class.java)
        startActivity(intent)
        finish()
        return true
    }
}
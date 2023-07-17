package com.example.vakifbank_case.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import androidx.activity.ComponentActivity
import com.example.vakifbank_case.R

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun buttonClick(view: View) {
        val editText = findViewById<androidx.appcompat.widget.AppCompatEditText>(R.id.editTextText2) as EditText
        val apikey = editText.text.toString()
        if (apikey != "") {
            val intent = Intent(this@MainActivity, SecondPage::class.java)
            intent.putExtra("apikey", apikey)
            startActivity(
                intent
            )
        }
        else{
            val intent = Intent(this@MainActivity, SecondPage::class.java)
            startActivity(
                intent
            )
        }
    }
}
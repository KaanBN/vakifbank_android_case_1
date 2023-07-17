package com.example.vakifbank_case.view

import android.app.AlertDialog
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
        var data: String = ""

        if (intent.data != null) {
            data = intent.data!!.getQueryParameter("apikey").toString()
            val editText = findViewById<androidx.appcompat.widget.AppCompatEditText>(R.id.editTextText2) as EditText
            editText.setText(data)
        } else {
            println("Boş")
        }
    }

    fun buttonClick(view: View) {
        val editText =
            findViewById<androidx.appcompat.widget.AppCompatEditText>(R.id.editTextText2) as EditText
        val apikey = editText.text.toString()
        println("Apikey:$apikey")
        if(apikey.trim().length > 0){
            val intent = Intent(this@MainActivity, SecondPage::class.java)
            intent.putExtra("apikey", apikey)
            startActivity(
                intent
            )
        }
        else {
            val builder = AlertDialog.Builder(this)
//            builder.setTitle("Api Key can't be empty")
            builder.setMessage("Api Key can't be empty")
            builder.setPositiveButton(android.R.string.ok) { dialog, which ->
                println("clicked ok")
            }
            builder.show()
        }
    }
}
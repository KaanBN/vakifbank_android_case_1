package com.example.vakifbank_case

import android.widget.ImageView
import com.squareup.picasso.Picasso

fun ImageView.loadImage(image: String){
    val url = "https://openweathermap.org/img/wn/$image.png"
    Picasso.get().load(url).into(this)
}

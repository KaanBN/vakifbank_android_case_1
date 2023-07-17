package com.example.vakifbank_case.adapter

import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.example.vakifbank_case.R
import com.example.vakifbank_case.model.WeatherResponse
import java.time.Instant
import java.time.ZoneId
import java.time.format.TextStyle
import java.util.Locale
import com.squareup.picasso.Picasso


class WeatherAdapter(private val mList: WeatherResponse) : RecyclerView.Adapter<WeatherAdapter.ViewHolder> () {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.weather_item, parent, false)

        return ViewHolder(view)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: WeatherAdapter.ViewHolder, position: Int) {
        val weather = mList
        val daily = weather.daily[position]
        val dayDegree = (daily.temp.day - 273.15).toInt()
        val nightDegree = (daily.temp.night - 273.15).toInt()
        holder.rowDayDegTextView.text = "$dayDegree°"
        holder.rowNightDegreeTextView.text = "$nightDegree°"
        val dateTime = daily.dt
        val instant = Instant.ofEpochSecond(dateTime.toLong())
        val dayOfWeek = instant.atZone(ZoneId.systemDefault()).dayOfWeek
        val dayName = dayOfWeek.getDisplayName(TextStyle.FULL, Locale.getDefault())
        holder.rowLocationTextView.text = dayName
        val image = daily.weather[0].icon
        val url = "https://openweathermap.org/img/wn/$image.png"
        Picasso.get().load(url).into(holder.rowWeatherIconImageView)
    }

    override fun getItemCount(): Int {
        return mList.daily.size
    }

    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val rowLocationTextView: TextView = itemView.findViewById(R.id.rowLocationTextView)
        val rowDayDegTextView: TextView = itemView.findViewById(R.id.rowDayDegTextView)
        val rowNightDegreeTextView: TextView = itemView.findViewById(R.id.rowNightDegreeTextView)
        val rowWeatherIconImageView: ImageView = itemView.findViewById(R.id.rowWeatherIconImageView)
    }

}
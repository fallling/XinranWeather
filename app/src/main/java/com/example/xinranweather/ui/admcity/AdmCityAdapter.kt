package com.example.xinranweather.ui.admcity

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.recyclerview.widget.RecyclerView
import com.example.xinranweather.R
import com.example.xinranweather.logic.Repository
import com.example.xinranweather.logic.model.Location
import com.example.xinranweather.logic.model.Place
import com.example.xinranweather.logic.model.getSky

/**
User: FALL
Data: 2021/1/3
Time: 11:03
 */

class AdmCityAdapter(private val activity: AdmCity, private val placeList: List<Place>) :
        RecyclerView.Adapter<AdmCityAdapter.ViewHolder>() {
    private val locationLiveData = MutableLiveData<Location>()
    var locationLng = ""
    var locationLat = ""
    var placeName = ""
    val weatherLiveData = Transformations.switchMap(locationLiveData) { location ->
        Repository.refreshWeather(location.lng, location.lat)
    }

    fun refreshWeather(lng: String, lat: String) {
        locationLiveData.value = Location(lng, lat)
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val placeName: TextView = view.findViewById(R.id.placeName)
        val temperatureInfo: TextView = view.findViewById(R.id.temperatureInfo)
        val skyInfo: TextView = view.findViewById(R.id.skyInfo)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.adm_city_item, parent, false)

        val holder = ViewHolder(view)
        holder.itemView.setOnClickListener {
            val position = holder.adapterPosition
            val place = placeList[position]
        }
        return holder
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val place = placeList[position]
        holder.placeName.text = place.name
        refreshWeather(place.location.lng, place.location.lat)
        weatherLiveData.observe(this.activity, { result ->
            val weather = result.getOrNull()
            if (weather != null) {
                holder.temperatureInfo.text = "${weather.realtime.temperature.toInt()}" + "°C"
                holder.skyInfo.text = "${getSky(weather.realtime.skycon).info}"
            } else {
                Toast.makeText(this.activity, "无法成功获取天气信息", Toast.LENGTH_SHORT).show()
                result.exceptionOrNull()?.printStackTrace()
            }
        })
    }

    override fun getItemCount() = placeList.size
}
package com.example.xinranweather.logic.dao

import android.content.Context
import androidx.core.content.edit
import com.example.xinranweather.XinranWeatherApplication
import com.example.xinranweather.logic.model.Place
import com.google.gson.Gson

/**
User: FALL
Data: 2020/12/30
Time: 23:41
 */

object PlaceDao {
    fun savePlace(place: Place) {
        sharedPreferences().edit {
            putString("place", Gson().toJson(place))
        }
    }

    fun getSavedPlace(): Place {
        val placeJson = sharedPreferences().getString("place", "")
        return Gson().fromJson(placeJson, Place::class.java)
    }

    fun isPlaceSaved() = sharedPreferences().contains("place")

    private fun sharedPreferences() = XinranWeatherApplication.context.getSharedPreferences("Xinran_weather", Context.MODE_PRIVATE)
}
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

    fun getSavedPlace(): Place {
        val placeJson = sharedPreferences().getString("place_1", "")
        return Gson().fromJson(placeJson, Place::class.java)
    }

    //fun isPlaceSaved() = sharedPreferences().contains("place")

    fun isPlaceSaved(place: Place): Boolean {
        val placeList: ArrayList<Place> = getAllPlaces()
        if (placeList.contains(place) || place.name == getLocalPlace().name) {
            return true;
        }
        return false
    }


    private fun sharedPreferences() = XinranWeatherApplication.context.getSharedPreferences("Xinran_weather", Context.MODE_PRIVATE)

    fun savePlaces(placeList: ArrayList<Place>) {
        sharedPreferences().edit {
            putInt("place_size", placeList.size)
            for (i in 1..placeList.size) {
                remove("place_$i")
                putString("place_$i", Gson().toJson(placeList[i - 1]))
            }
        }
    }

    fun savePlace(place: Place) {
        if (!isPlaceSaved(place)) {
            val placeList = getAllPlaces()
            placeList.add(place)
            savePlaces(placeList)
        }
    }

    fun saveLocalPlace(place: Place) {
        sharedPreferences().edit {
            putString("place_local", Gson().toJson(place))
        }
    }

    fun getLocalPlace(): Place {
        val placeJson = sharedPreferences().getString("place_local", "")
        return Gson().fromJson(placeJson, Place::class.java)
    }

    fun getAllPlaces(): ArrayList<Place> {
        val size = sharedPreferences().getInt("place_size", 0)
        val placeList: ArrayList<Place> = arrayListOf()
        for (i in 1..size) {
            val placeJson = sharedPreferences().getString("place_$i", "")
            val place = Gson().fromJson(placeJson, Place::class.java)
            placeList.add(place)
        }
        return placeList
    }

}
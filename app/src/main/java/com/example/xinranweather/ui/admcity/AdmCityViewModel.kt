package com.example.xinranweather.ui.admcity

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.xinranweather.logic.Repository
import com.example.xinranweather.logic.model.Place

/**
User: FALL
Data: 2021/1/4
Time: 1:04
 */
class AdmCityViewModel : ViewModel() {
    private val searchLiveData = MutableLiveData<String>()

    val placeList = ArrayList<Place>()
    val placeLiveData = Transformations.switchMap(searchLiveData) { query ->
        Repository.searchPlaces(query)
    }

    fun searchPlaces(query: String) {
        searchLiveData.value = query
    }

    fun savePlace(place: Place) = Repository.savePlace(place)
    fun getSavedPlace() = Repository.getSavedPlace()
    fun isPlaceSaved(place: Place) = Repository.isPlaceSaved(place)
    fun getAllSavedPlace() = Repository.getAllSavedPlaces()
    fun getLocalPlace() = Repository.getLocalPlace()
}
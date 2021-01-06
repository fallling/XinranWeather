package com.example.xinranweather.ui.admcity

import androidx.lifecycle.ViewModel
import com.example.xinranweather.logic.Repository

/**
User: FALL
Data: 2021/1/4
Time: 1:04
 */

class AdmCityViewModel : ViewModel() {
    fun getAllSavedPlace() = Repository.getAllSavedPlaces()
    fun getLocalPlace() = Repository.getLocalPlace()
}

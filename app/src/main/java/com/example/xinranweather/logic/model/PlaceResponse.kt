package com.example.xinranweather.logic.model

import com.google.gson.annotations.SerializedName

/**
User: FALL
Data: 2020/12/29
Time: 12:32
 */

data class PlaceResponse(val status: String, val places: List<Place>)
data class Place(
    val name: String,
    val location: Location,
    @SerializedName("formatted_address") val address: String
)

data class Location(val lng: String, val lat: String)
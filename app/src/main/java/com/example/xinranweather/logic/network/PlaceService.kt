package com.example.xinranweather.logic.network

import com.example.xinranweather.XinranWeatherApplication
import com.example.xinranweather.logic.model.PlaceResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

/**
User: FALL
Data: 2020/12/28
Time: 23:22
 */

interface PlaceService {
    //https:api.caiyunapp.com/v2/place?query=长沙&token=qq8ZCrKGgM6Z6i81}&lang=zh_CN

    @GET("v2/place?token=${XinranWeatherApplication.TOKEN}&lang=zh_CN")
    fun searchPlaces(@Query("query") query: String): Call<PlaceResponse>
}
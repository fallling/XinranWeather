package com.example.xinranweather.logic.network

import com.example.xinranweather.XinranWeatherApplication
import com.example.xinranweather.logic.model.DailyResponse
import com.example.xinranweather.logic.model.RealtimeResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

/**
User: FALL
Data: 2020/12/30
Time: 0:33
 */
interface WeatherService {

    //https:api.caiyunapp.com/v2.5/qq8ZCrKGgM6Z6i81/112.938814,28.228209/realtime.json
    @GET("v2.5/${XinranWeatherApplication.TOKEN}/{lng},{lat}/realtime.json")
    fun getRealtimeWeather(
            @Path("lng") lng: String,
            @Path("lat") lat: String
    ): Call<RealtimeResponse>

    @GET("v2.5/${XinranWeatherApplication.TOKEN}/{lng},{lat}/daily.json")
    fun getDailyWeather(@Path("lng") lng: String, @Path("lat") lat: String): Call<DailyResponse>
}
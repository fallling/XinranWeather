package com.example.xinranweather.logic.model

import com.google.gson.annotations.SerializedName

/**
User: FALL
Data: 2020/12/30
Time: 0:21
 */
data class RealtimeResponse(val status: String, val result: Result) {
    data class Result(val realtime: Realtime)
    data class Realtime(
        val skycon: String,
        val temperature: Float,
        @SerializedName("air_quality") val airQuality: AirQuality
    )

    data class AirQuality(val aqi: AQI)
    data class AQI(val chn: Float)
}
package com.example.xinranweather.logic

import androidx.lifecycle.liveData
import com.example.xinranweather.logic.network.XinranWeatherNetwork
import kotlinx.coroutines.Dispatchers
import kotlin.coroutines.CoroutineContext

/**
User: FALL
Data: 2020/12/29
Time: 12:46
 */

object Repository {

    fun searchPlaces(query: String) = fire(Dispatchers.IO) {
        val placeResponse = XinranWeatherNetwork.searchPlaces(query)
        if (placeResponse.status == "ok") {
            val places = placeResponse.places
            Result.success(places)
        } else {
            Result.failure(RuntimeException("response status is ${placeResponse.status}"))
        }
    }

    private fun <T> fire(context: CoroutineContext, block: suspend () -> Result<T>) =
        liveData<Result<T>>(context) {
            val result = try {
                block()
            } catch (e: Exception) {
                Result.failure<T>(e)
            }
            emit(result)
        }
}

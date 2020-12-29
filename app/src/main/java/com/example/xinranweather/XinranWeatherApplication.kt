package com.example.xinranweather

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context

/**
User: FALL
Data: 2020/12/28
Time: 23:05
 */

class XinranWeatherApplication : Application() {
    companion object {
        const val TOKEN = "qq8ZCrKGgM6Z6i81"

        @SuppressLint("StaticFieldLeak")
        lateinit var context: Context
    }

    override fun onCreate() {
        super.onCreate()
        context = applicationContext
    }
}
package com.example.xinranweather

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /*hello*/
        val textView:TextView = findViewById(R.id.textView)
        textView.text = "nihao"
        textView.textSize = 15f
    }
}
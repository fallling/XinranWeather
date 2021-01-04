package com.example.xinranweather.ui.admcity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.xinranweather.R
import com.example.xinranweather.logic.model.Location
import com.example.xinranweather.logic.model.Place
import kotlinx.android.synthetic.main.activity_adm_city.*

class AdmCity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_adm_city)
        val placeList = ArrayList<Place>()
        placeList.add(Place("长沙", Location("112.938814", "28.228209"), "湖南省长沙市"))
        placeList.add(Place("长沙", Location("112.938814", "28.228209"), "湖南省长沙市"))
        placeList.add(Place("长沙", Location("112.938814", "28.228209"), "湖南省长沙市"))
        placeList.add(Place("长沙", Location("112.938814", "28.228209"), "湖南省长沙市"))
        placeList.add(Place("长沙", Location("112.938814", "28.228209"), "湖南省长沙市"))
        placeList.add(Place("长沙", Location("112.938814", "28.228209"), "湖南省长沙市"))
        placeList.add(Place("长沙", Location("112.938814", "28.228209"), "湖南省长沙市"))
        placeList.add(Place("长沙", Location("112.938814", "28.228209"), "湖南省长沙市"))
        placeList.add(Place("长沙", Location("112.938814", "28.228209"), "湖南省长沙市"))
        val layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager
        val adapter = AdmCityAdapter(this, placeList)
        recyclerView.adapter = adapter
    }
}
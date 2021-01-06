package com.example.xinranweather.ui.admcity

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.xinranweather.R
import com.example.xinranweather.logic.model.Place
import com.example.xinranweather.ui.place.PlaceActivity
import kotlinx.android.synthetic.main.activity_adm_city.*

class AdmCity : AppCompatActivity() {
    val viewModel by lazy { ViewModelProvider(this).get(AdmCityViewModel::class.java) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_adm_city)

        //设置toolbar
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        //加载数据
        val placeList = ArrayList<Place>()
        val place = viewModel.getLocalPlace()
        placeList.add(place)
        placeList.addAll(viewModel.getAllSavedPlace())
        //设置recycleView适配器
        val layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager
        val adapter = AdmCityAdapter(this, placeList)
        recyclerView.adapter = adapter

        addCity.setOnClickListener {
            val intent = Intent(this, PlaceActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                this.finish()
            }
        }
        return true
    }
}
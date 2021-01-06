package com.example.xinranweather

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.amap.api.location.AMapLocation
import com.amap.api.location.AMapLocationClient
import com.amap.api.location.AMapLocationClientOption
import com.amap.api.location.AMapLocationListener
import com.example.xinranweather.XinranWeatherApplication.Companion.context
import com.example.xinranweather.logic.Repository
import com.example.xinranweather.logic.model.Location
import com.example.xinranweather.logic.model.Place
import com.example.xinranweather.ui.admcity.AdmCity
import com.example.xinranweather.ui.weather.WeatherActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var mLocationClient: AMapLocationClient? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        initMap()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.admCity -> {
                val intent = Intent(this, AdmCity::class.java)
                startActivity(intent)
            }
        }
        return true
    }

    private fun initMap() {
        //初始化定位
        val mLocationClient = AMapLocationClient(context)
        //设置定位回调监听
        mLocationClient.setLocationListener(mLocationListener)
        val mLocationOption = AMapLocationClientOption()
        //设置定位模式为高精度模式，AMapLocationMode.Battery_Saving为低功耗模式，AMapLocationMode.Device_Sensors是仅设备模式
        mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy)
        mLocationOption.setNeedAddress(true) //设置是否返回地址信息（默认返回地址信息）
        mLocationOption.setMockEnable(false) //设置是否允许模拟位置,默认为false，不允许模拟位置
        mLocationOption.setInterval(15000) //设置定位间隔,单位毫秒,默认为2000ms
        mLocationOption.setOnceLocation(true) //可选，是否设置单次定位默认为false即持续定位
        mLocationOption.setOnceLocationLatest(false) //可选，设置是否等待wifi刷新，默认为false.如果设置为true,会自动变为单次定位，持续定位时不要使用
        mLocationOption.setWifiScan(true) //可选，设置是否开启wifi扫描。默认为true，如果设置为false会同时停止主动刷新，停止以后完全依赖于系统刷新，定位位置可能存在误差
        mLocationOption.setLocationCacheEnable(true) //可选，设置是否使用缓存定位，默认为true
        //给定位客户端对象设置定位参数
        mLocationClient.setLocationOption(mLocationOption)
        //启动定位
        mLocationClient.startLocation()
    }

    var mLocationListener: AMapLocationListener = object : AMapLocationListener {
        override fun onLocationChanged(aMapLocation: AMapLocation?) {
            if (aMapLocation != null) {
                if (aMapLocation.getErrorCode() == 0) {
                    //定位成功回调信息，设置相关消息
                    aMapLocation.getLocationType() //获取当前定位结果来源，如网络定位结果，详见定位类型表
                    // aMapLocation.getLatitude();//获取纬度
                    // aMapLocation.getLongitude();//获取经度
                    val mLocation = Location(
                            aMapLocation.longitude.toString(),
                            aMapLocation.latitude.toString()
                    )
                    val place = Place(aMapLocation.district, mLocation, aMapLocation.address)
                    val intent = Intent(context, WeatherActivity::class.java).apply {
                        putExtra("location_lng", place.location.lng)
                        putExtra("location_lat", place.location.lat)
                        putExtra("place_name", place.name)
                        putExtra("is_locale", true)
                    }
                    mLocationClient?.stopLocation() //停止定位
                    Repository.saveLocalPlace(place)
                    startActivity(intent)
                    finish()
                } else {
                    //显示错误信息ErrCode是错误码，errInfo是错误信息，详见错误码表。
                    Log.e(
                        "info", ("location Error, ErrCode:"
                                + aMapLocation.getErrorCode()) + ", errInfo:"
                                + aMapLocation.getErrorInfo()
                    )
                }
            }
        }
    }

    override fun onStart() {
        super.onStart()
        Log.d("MainActivity", "onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.d("MainActivity", "onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.d("MainActivity", "onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.d("MainActivity", "onStop")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d("MainActivity", "onRestart")
    }

    override fun onDestroy() {
        super.onDestroy()
        mLocationClient?.onDestroy()
        Log.d("MainActivity", "onDestroy")
    }
}
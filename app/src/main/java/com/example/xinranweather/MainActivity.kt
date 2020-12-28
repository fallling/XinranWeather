package com.example.xinranweather

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.amap.api.location.AMapLocationClient
import com.amap.api.location.AMapLocationListener

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //异步获取定位结果
        val mAMapLocationListener = AMapLocationListener { amapLocation ->
            if (amapLocation != null) {
                if (amapLocation.getErrorCode() == 0) {
                    //可在其中解析amapLocation获取相应内容。
                }else {
                    //定位失败时，可通过ErrCode（错误码）信息来确定失败的原因，errInfo是错误信息，详见错误码表。
                    Log.e("AmapError", "location Error, ErrCode:"
                            + amapLocation.getErrorCode() + ", errInfo:"
                            + amapLocation.getErrorInfo())
                }
            }
            System.out.println(amapLocation.latitude + amapLocation.longitude)
        }
        //声明AMapLocationClient类对象
        val mLocationClient = AMapLocationClient(getApplicationContext())
        //设置定位回调监听
        mLocationClient.setLocationListener(mAMapLocationListener)
        //启动定位
        mLocationClient.startLocation()
    }
}
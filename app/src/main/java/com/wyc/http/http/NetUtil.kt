package com.wyc.http.http

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import com.wyc.http.App

/**
 *作者： wyc
 * <p>
 * 创建时间： 2019/2/16 17:22
 * <p>
 * 文件名字： com.wyc.http.http
 * <p>
 * 类的介绍：
 */
object NetUtil {

    @JvmStatic
    fun isNetworkConnected(): Boolean {
        val connectivityManager = App.context().getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = connectivityManager.activeNetworkInfo as NetworkInfo
        return networkInfo.isConnected
    }

    @JvmStatic
    fun isWifiConnected(context: Context): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI) as NetworkInfo
        return networkInfo.isAvailable
    }
}
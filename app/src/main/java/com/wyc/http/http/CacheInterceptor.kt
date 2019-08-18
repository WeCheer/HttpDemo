package com.wyc.http.http

import com.wyc.http.App
import okhttp3.Cache
import okhttp3.CacheControl
import okhttp3.Interceptor
import okhttp3.Response
import java.io.File

/**
 *作者： wyc
 * <p>
 * 创建时间： 2019/2/16 17:20
 * <p>
 * 文件名字： com.wyc.http.http
 * <p>
 * 类的介绍：
 */
class CacheInterceptor : Interceptor {

    private val CACHE_NAME: String = "cache"
    private var cache: Cache

    init {
        val cacheFile = File(App.context().externalCacheDir, CACHE_NAME)
        cache = Cache(cacheFile, 1024 * 1024 * 50)
    }

    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()
        if (NetUtil.isNetworkConnected()) {
            request = request.newBuilder()
                    .cacheControl(CacheControl.FORCE_CACHE)
                    .build()
        }
        val response = chain.proceed(request)
        if (NetUtil.isNetworkConnected()) {
            val maxAge = 0
            // 有网络时 设置缓存超时时间0个小时
            response.newBuilder()
                    .header("Cache-Control", "public,max-age=$maxAge")
                    //// 清除头信息，因为服务器如果不支持，会返回一些干扰信息，不清除下面无法生效
                    .removeHeader(CACHE_NAME)
                    .build()
        } else {
            // 无网络时，设置超时为4周
            val maxStale = 60 * 60 * 24 * 28
            response.newBuilder()
                    .header("Cache-Control", "public, only-if-cached, max-stale=$maxStale")
                    .build()
        }
        return response
    }
}
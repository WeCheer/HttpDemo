package com.wyc.http.http

import okhttp3.Interceptor
import okhttp3.Response

/**
 *作者： wyc
 * <p>
 * 创建时间： 2019/2/16 18:07
 * <p>
 * 文件名字： com.wyc.http.http
 * <p>
 * 类的介绍：
 */
class HeaderInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val requestBuilder = originalRequest.newBuilder()
                .addHeader("Accept- Xml.Encoding", "gzip")
                .addHeader("Accept", "application/json")
                .addHeader("Content-Type", "application/json; charset=utf-8")
                .method(originalRequest.method(), originalRequest.body())
        requestBuilder.addHeader("Authorization", "")
        val request = requestBuilder.build()
        return chain.proceed(request)
    }
}
package com.wyc.http.http

import android.os.Environment
import android.util.Log
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit

/**
 *作者： wyc
 * <p>
 * 创建时间： 2019/2/16 9:23
 * <p>
 * 文件名字： com.wyc.http.http
 * <p>
 * 类的介绍：
 */
class RetrofitManager private constructor() {

    private val mTAG = "RetrofitManager"

    private val DEFAULT_TIME_OUT: Long = 10
    private val DEFAULT_READ_TIME: Long = 10
    private val DEFAULT_WRITE_TIME: Long = 10

    private lateinit var mRetrofit: Retrofit
    private val mBuilder: OkHttpClient.Builder = OkHttpClient.Builder()

    companion object {
        val instance: RetrofitManager = SingletonHolder.INSTANCE
    }

    private object SingletonHolder {
        val INSTANCE = RetrofitManager()
    }

    init {
        val cachePath = Environment.getExternalStorageDirectory().absolutePath + "/RxJavaDemo"
        mBuilder.connectTimeout(DEFAULT_TIME_OUT, TimeUnit.SECONDS)
                .readTimeout(DEFAULT_READ_TIME, TimeUnit.SECONDS)
                .writeTimeout(DEFAULT_WRITE_TIME, TimeUnit.SECONDS)
                .cache(Cache(File(cachePath), 1024 * 1024 * 10))
                .sslSocketFactory(SSLSocketFactory.getSSLSocketFactory())
                .hostnameVerifier(SSLSocketFactory.getHostnameVerifier())
        addInterceptor(mBuilder)
    }

    /**
     * 添加拦截器
     */
    private fun addInterceptor(builder: OkHttpClient.Builder) {
        //添加日志拦截器
        val loggingInterceptor = HttpLoggingInterceptor { message ->
            Log.d(mTAG, message)
        }
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        builder.addInterceptor(loggingInterceptor)
    }

    @JvmOverloads
    fun create(url: String = Constants.BASE_URL): ApiService {
        mRetrofit = Retrofit.Builder()
                .client(mBuilder.build())
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
        return mRetrofit.create(ApiService::class.java)
    }

}
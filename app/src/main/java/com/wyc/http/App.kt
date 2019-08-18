package com.wyc.http

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context

/**
 *作者： wyc
 * <p>
 * 创建时间： 2019/2/16 17:31
 * <p>
 * 文件名字： com.wyc.http
 * <p>
 * 类的介绍：
 */
class App : Application() {
    companion object {
        @SuppressLint("StaticFieldLeak")
        private var mContext: Context? = null
        @SuppressLint("StaticFieldLeak")
        private var mInstance: App? = null

        fun instance() = mInstance!!
        fun context() = mContext!!
    }

    override fun onCreate() {
        super.onCreate()
        mContext = this.applicationContext
        mInstance = this
    }
}

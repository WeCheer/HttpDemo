package com.wyc.http.http

/**
 *作者： wyc
 * <p>
 * 创建时间： 2019/2/16 9:53
 * <p>
 * 文件名字： com.wyc.http.http
 * <p>
 * 类的介绍：
 */
interface CallBack<T> {

    fun success(t: T)

    fun failed(e: Throwable)
}
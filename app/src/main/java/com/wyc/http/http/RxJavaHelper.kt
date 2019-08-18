package com.wyc.http.http

import io.reactivex.ObservableTransformer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 *作者： wyc
 * <p>
 * 创建时间： 2019/2/16 10:21
 * <p>
 * 文件名字： com.wyc.http.http
 * <p>
 * 类的介绍：
 */
object RxJavaHelper {

    @JvmStatic
    fun <T> observeOnMainThread(): ObservableTransformer<T, T> {
        return ObservableTransformer {
            it.subscribeOn(Schedulers.io())
                    .unsubscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
        }
    }
}
package com.wyc.http.http

import android.app.ProgressDialog
import android.content.Context
import android.util.Log
import io.reactivex.Observer
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

/**
 *作者： wyc
 * <p>
 * 创建时间： 2019/2/16 9:47
 * <p>
 * 文件名字： com.wyc.http.http
 * <p>
 * 类的介绍：
 */
abstract class RxSubscriber<T>(context: Context) : Observer<T> {

    private val mTag = "RxSubscriber"

    private var mProgressDialog: ProgressDialog? = null
    private var mDisposable: Disposable? = null
    private var mContext: Context = context

    override fun onComplete() {
        dismissLoading()
        Log.d(mTag, "onComplete")
    }

    override fun onSubscribe(d: Disposable) {
        mDisposable = CompositeDisposable()
        showLoading()
    }

    override fun onNext(t: T) {
        success(t)
    }

    override fun onError(e: Throwable) {
        failed(e)
        dismissLoading()
    }

    private fun showLoading() {
        if (mProgressDialog == null) {
            mProgressDialog = ProgressDialog(mContext)
            mProgressDialog!!.setMessage("正在加载...")
            mProgressDialog!!.show()
        }
    }

    private fun dismissLoading() {
        mProgressDialog?.dismiss()
    }

    abstract fun success(t: T)
    abstract fun failed(e: Throwable)
}
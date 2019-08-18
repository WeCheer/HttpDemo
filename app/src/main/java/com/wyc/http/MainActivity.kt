package com.wyc.http

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.method.ScrollingMovementMethod
import android.util.Log
import com.wyc.http.http.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val mTAG = "MainActivity"
    private val start: Int = 0
    private val count: Int = 5

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        httpDataText.movementMethod = ScrollingMovementMethod.getInstance()
        RetrofitManager.instance.create()
                .getMovieTop250(start.toString(), count.toString())
                .compose(RxJavaHelper.observeOnMainThread<MovieBean>())
                .subscribe(object : RxSubscriber<MovieBean>(this) {
                    override fun success(t: MovieBean) {
                        Log.d("TAG", FormatTool.printJson(t, "hahhaha"))
                        httpDataText.text = FormatTool.printJson(t, "hahhaha")
                    }

                    override fun failed(e: Throwable) {
                        Log.d(mTAG, e.message)
                    }
                })
    }
}

package com.wyc.http;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.wyc.http.http.MovieBean;
import com.wyc.http.http.RetrofitManager;
import com.wyc.http.http.RxJavaHelper;
import com.wyc.http.http.RxSubscriber;

import org.jetbrains.annotations.NotNull;

public class TestActivity extends AppCompatActivity {
    private int start = 0;
    private int count = 5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        RetrofitManager.Companion.getInstance().create()
                .getMovieTop250(String.valueOf(start), String.valueOf(count))
                .compose(RxJavaHelper.<MovieBean>observeOnMainThread())
                .subscribe(new RxSubscriber<MovieBean>(this) {
                    @Override
                    public void failed(@NotNull Throwable e) {
                    }

                    @Override
                    public void success(MovieBean movieBean) {
                    }
                });
    }
}

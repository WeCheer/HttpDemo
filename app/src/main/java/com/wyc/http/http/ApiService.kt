package com.wyc.http.http

import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

/**
 *作者： wyc
 * <p>
 * 创建时间： 2019/2/16 10:28
 * <p>
 * 文件名字： com.wyc.http.http
 * <p>
 * 类的介绍：
 */
interface ApiService {

    @GET("v2/movie/top250")
    fun getMovieTop250(
            @Query("start") start: String,
            @Query("count") count: String
    ): Observable<MovieBean>
}
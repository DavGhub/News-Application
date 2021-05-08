package com.davit.kotlin.news.network

import com.davit.kotlin.news.models.NewsModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitAPI {

//    @Headers("Content-Type: application/json")
    @GET("articles")
    fun fetchNews(@Query("_limit")limit:Int,@Query("_start")start:Int):Call<NewsModel>
//
}
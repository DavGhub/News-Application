package com.davit.kotlin.volonewstask.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val NEWS_BASE_URL = "https://test.spaceflightnewsapi.net/api/v2/"

object RetrofitApiService {

    //

    private var retrofit: Retrofit? = null


    fun getNewsService():Retrofit? {
        if (retrofit == null) {
            retrofit =Retrofit.Builder()
                .baseUrl(NEWS_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
        return retrofit
    }
}
package com.davit.kotlin.volonewstask.paging.sourcefactory

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.davit.kotlin.volonewstask.models.NewsModel
import com.davit.kotlin.volonewstask.paging.datasources.NewsDataSource

//class NewsDataSourceFactory : DataSource.Factory<Long, NewsModel>() {
//
////    private lateinit var newsDataSource: NewsDataSource
//    val dataSourceMutableLiveData = MutableLiveData<NewsDataSource>()
//
//    override fun create(): DataSource<Long, NewsModel> {
//        val newsDataSource = NewsDataSource()
//        dataSourceMutableLiveData.postValue(newsDataSource)
//        return newsDataSource
//    }
//
//
//}
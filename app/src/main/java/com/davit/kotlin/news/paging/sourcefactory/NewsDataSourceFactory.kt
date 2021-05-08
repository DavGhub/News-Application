package com.davit.kotlin.news.paging.sourcefactory

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.davit.kotlin.news.models.NewsModelItem
import com.davit.kotlin.news.paging.datasources.NewsDataSource

class NewsDataSourceFactory : DataSource.Factory<Long, NewsModelItem>() {

    val dataSourceMutableLiveData = MutableLiveData<NewsDataSource>()

    override fun create(): DataSource<Long, NewsModelItem> {
        val newsDataSource = NewsDataSource()
        dataSourceMutableLiveData.postValue(newsDataSource)
        return newsDataSource
    }

}
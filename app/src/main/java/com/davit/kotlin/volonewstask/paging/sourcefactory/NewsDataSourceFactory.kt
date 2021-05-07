package com.davit.kotlin.volonewstask.paging.sourcefactory

//class NewsDataSourceFactory : DataSource.Factory<Long, NewsModel>() {
//
//    private lateinit var newsDataSource: NewsDataSource
//    val dataSourceMutableLiveData = MutableLiveData<NewsDataSource>()
//
//    override fun create(): DataSource<Long, NewsModel> {
//        newsDataSource = NewsDataSource()
//        dataSourceMutableLiveData.postValue(newsDataSource)
//        return newsDataSource
//    }
//}
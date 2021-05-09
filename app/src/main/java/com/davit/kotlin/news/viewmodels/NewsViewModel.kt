package com.davit.kotlin.news.viewmodels

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.davit.kotlin.news.database.NewsEntity
import com.davit.kotlin.news.models.NewsModel
import com.davit.kotlin.news.models.NewsModelItem
import com.davit.kotlin.news.paging.datasources.NewsDataSource
import com.davit.kotlin.news.paging.sourcefactory.NewsDataSourceFactory
import com.davit.kotlin.news.repository.NewsRepository

class NewsViewModel : ViewModel() {

    private val _newsLiveData = MutableLiveData<NewsModel>()
    val newsLiveData: LiveData<NewsModel> = _newsLiveData

    private val _failRequestLiveData = MutableLiveData<String>()
    val failRequestLiveData: LiveData<String> = _failRequestLiveData

//    private val newsService = RetrofitApiService.getNewsService()?.create(RetrofitAPI::class.java)

    var newsEntityLiveData: LiveData<List<NewsEntity>>? = null

    private var newsPagedList:LiveData<PagedList<NewsModelItem>>
    private var liveDataSource:LiveData<NewsDataSource>

    init {
        val newsSourceFactory = NewsDataSourceFactory()
        liveDataSource = newsSourceFactory.dataSourceMutableLiveData

        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(true)
            .setPageSize(10)
            .build()

        newsPagedList = LivePagedListBuilder(newsSourceFactory,config).build()
    }

    fun getPagedLiveData():LiveData<PagedList<NewsModelItem>>{
        return newsPagedList
    }

    fun insertData(context: Context, news: NewsModelItem) {
        NewsRepository.insertData(context,news)
    }

    fun getSavedNewsLiveData(context: Context) : LiveData<List<NewsEntity>>? {
        newsEntityLiveData = NewsRepository.getNews(context)
        return newsEntityLiveData
    }

    fun deleteNewsFromFavorites(context: Context,news: NewsModelItem){
        NewsRepository.deleteNews(context,news)
    }

//    fun fetchNews(){
//        viewModelScope.launch(Dispatchers.Default) {
//            newsService?.fetchNews(30,0)?.enqueue(object: Callback<List<NewsModelItem>> {
//                override fun onResponse(call: Call<NewsModel>, response: Response<NewsModel>) {
//                    Log.e("Response","response: ${response.body().toString()}")
//                    Log.e("URL","url: ${response.raw().request().url()}")
//
//                    _newsLiveData.postValue(response.body())
//                }
//
//                override fun onFailure(call: Call<NewsModel>, t: Throwable) {
//                    _failRequestLiveData.postValue(t.message)
//                    Log.e("Fail","Error message: ${t.message}")
//                }
//
//            })
//        }
//    }
}
package com.davit.kotlin.volonewstask.viewmodels

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.davit.kotlin.volonewstask.database.NewsEntity
import com.davit.kotlin.volonewstask.models.NewsModel
import com.davit.kotlin.volonewstask.models.NewsModelItem
import com.davit.kotlin.volonewstask.network.RetrofitAPI
import com.davit.kotlin.volonewstask.network.RetrofitApiService
import com.davit.kotlin.volonewstask.repository.NewsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NewsViewModel : ViewModel() {

    private val _newsLiveData = MutableLiveData<NewsModel>()
    val newsLiveData: LiveData<NewsModel> = _newsLiveData

    private val _failRequestLiveData = MutableLiveData<String>()
    val failRequestLiveData: LiveData<String> = _failRequestLiveData

    private val newsService = RetrofitApiService.getNewsService()?.create(RetrofitAPI::class.java)


    var newsEntityLiveData: LiveData<List<NewsEntity>>? = null

    fun insertData(context: Context, news: NewsModelItem) {
        NewsRepository.insertData(context,news)
    }

    fun getSavedNewsLiveData(context: Context) : LiveData<List<NewsEntity>>? {
        newsEntityLiveData = NewsRepository.getNews(context)
        return newsEntityLiveData
    }

    fun fetchNews(){
        viewModelScope.launch(Dispatchers.Default) {
            newsService?.fetchNews(30,0)?.enqueue(object: Callback<NewsModel> {
                override fun onResponse(call: Call<NewsModel>, response: Response<NewsModel>) {
                    Log.e("Response","response: ${response.body().toString()}")
                    Log.e("URL","url: ${response.raw().request().url()}")

                    _newsLiveData.postValue(response.body())
                }

                override fun onFailure(call: Call<NewsModel>, t: Throwable) {
                    _failRequestLiveData.postValue(t.message)
                    Log.e("Fail","Error message: ${t.message}")
                }

            })
        }
    }
}
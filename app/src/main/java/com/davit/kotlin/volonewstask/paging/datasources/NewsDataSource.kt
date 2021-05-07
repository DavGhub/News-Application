package com.davit.kotlin.volonewstask.paging.datasources

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import com.davit.kotlin.volonewstask.models.NewsModel
import com.davit.kotlin.volonewstask.models.NewsModelItem
import com.davit.kotlin.volonewstask.network.RetrofitAPI
import com.davit.kotlin.volonewstask.network.RetrofitApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NewsDataSource : PageKeyedDataSource<Long,NewsModelItem>() {

    private val newsService = RetrofitApiService.getNewsService()?.create(RetrofitAPI::class.java)
    val newsLiveData = MutableLiveData<NewsModel>()

    override fun loadInitial(params: LoadInitialParams<Long>, callback: LoadInitialCallback<Long, NewsModelItem>) {
        newsService?.fetchNews(10,0)?.enqueue(object: Callback<NewsModel> {
            override fun onResponse(call: Call<NewsModel>, response: Response<NewsModel>) {
                Log.e("Response","response: ${response.body().toString()}")
                Log.e("URL","url: ${response.raw().request().url()}")
                response.body()?.data?.let { callback.onResult(it, null, 2L) }
                newsLiveData.postValue(response.body())
            }

            override fun onFailure(call: Call<NewsModel>, t: Throwable) {
//                _failRequestLiveData.postValue(t.message)
                Log.e("Fail","Error message: ${t.message}")
            }

        })
    }

    override fun loadBefore(params: LoadParams<Long>, callback: LoadCallback<Long, NewsModelItem>) {
        TODO("Not yet implemented")
    }

    override fun loadAfter(params: LoadParams<Long>, callback: LoadCallback<Long, NewsModelItem>) {
        newsService?.fetchNews(10,(params.key).toInt())?.enqueue(object: Callback<NewsModel> {
            override fun onResponse(call: Call<NewsModel>, response: Response<NewsModel>) {
                Log.e("Response","response: ${response.body().toString()}")
                Log.e("URL","url: ${response.raw().request().url()}")
                response.body()?.data?.let { callback.onResult(it,  params.key+1) }
                newsLiveData.postValue(response.body())
            }

            override fun onFailure(call: Call<NewsModel>, t: Throwable) {
                Log.e("Fail","Error message: ${t.message}")
            }

        })
    }
}
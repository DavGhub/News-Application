package com.davit.kotlin.news.repository

import android.content.Context
import androidx.lifecycle.LiveData
import com.davit.kotlin.news.database.NewsDatabase
import com.davit.kotlin.news.database.NewsEntity
import com.davit.kotlin.news.models.NewsModelItem
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch

class NewsRepository {

    companion object {

        var newsDatabase: NewsDatabase? = null

        var newsModelItem: LiveData<List<NewsEntity>>? = null

        private fun initializeDB(context: Context): NewsDatabase {
            return NewsDatabase.getDatabaseClient(context)
        }

        fun insertData(context: Context, news: NewsModelItem) {

            newsDatabase = initializeDB(context)

            CoroutineScope(IO).launch {
                val entityNews = NewsEntity(news.id, news.imageUrl, news.summary, news.title)
                newsDatabase!!.newsDao().insertNews(entityNews)
            }

        }

        fun getNews(context: Context): LiveData<List<NewsEntity>>? {

            newsDatabase = initializeDB(context)

            newsModelItem = newsDatabase!!.newsDao().getNews()

            return newsModelItem
        }

        fun deleteNews(context: Context, news: NewsModelItem) {
            newsDatabase = initializeDB(context)
            CoroutineScope(IO).launch {
                val deletingNews = NewsEntity(news.id, news.imageUrl, news.summary, news.title)
                newsDatabase!!.newsDao().deleteNews(deletingNews)
            }
        }

    }
}
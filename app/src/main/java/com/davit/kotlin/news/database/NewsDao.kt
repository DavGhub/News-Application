package com.davit.kotlin.news.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface NewsDao {
    //

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertNews(news: NewsEntity?)

    @Delete
    fun deleteNews(news: NewsEntity?)

    @Query("DELETE FROM news_table")
    fun deleteAllNewses()

    @Query("SELECT * FROM news_table")
    fun getNews(): LiveData<List<NewsEntity>>?
}
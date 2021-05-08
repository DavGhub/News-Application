package com.davit.kotlin.news.paging.sourcefactory;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;

import com.davit.kotlin.news.models.NewsModelItem;
import com.davit.kotlin.news.paging.datasources.NewsDataSource;

import org.jetbrains.annotations.NotNull;

public class NewsSourceFactory extends DataSource.Factory<Long, NewsModelItem> {

    private MutableLiveData<NewsDataSource> newsDataSourceMutableLiveData = new MutableLiveData<>();

    public MutableLiveData<NewsDataSource> getNewsDataSourceMutableLiveData() {
        return newsDataSourceMutableLiveData;
    }

    @NonNull
    @NotNull
    @Override
    public DataSource create() {
        NewsDataSource newsDataSource = new NewsDataSource();
        newsDataSourceMutableLiveData.postValue(newsDataSource);
        return newsDataSource;
    }
}

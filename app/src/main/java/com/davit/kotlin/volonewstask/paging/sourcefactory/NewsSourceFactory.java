package com.davit.kotlin.volonewstask.paging.sourcefactory;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;

import com.davit.kotlin.volonewstask.models.NewsModelItem;
import com.davit.kotlin.volonewstask.paging.datasources.NewsDataSource;

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
        return newsDataSource;
    }
}

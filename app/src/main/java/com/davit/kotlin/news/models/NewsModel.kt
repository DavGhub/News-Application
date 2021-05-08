package com.davit.kotlin.news.models

class NewsModel:ArrayList<NewsModelItem>(){
    val itemList by lazy { this.toList() }
}
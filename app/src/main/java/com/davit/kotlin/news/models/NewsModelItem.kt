package com.davit.kotlin.news.models

data class NewsModelItem(
    val events: List<Event>,
    val featured: Boolean,
    val id: String,
    val imageUrl: String,
    val launches: List<Any>,
    val newsSite: String,
    val publishedAt: String,
    val summary: String,
    val title: String,
    val updatedAt: String,
    val url: String
)
package com.davit.kotlin.volonewstask.database

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "news_table")
data class NewsEntity(

    @PrimaryKey
    @NonNull
    val id: String,
    val imageUrl: String,
    val summary: String,
    val title: String
)
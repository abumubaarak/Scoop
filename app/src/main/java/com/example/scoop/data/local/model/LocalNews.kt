package com.example.scoop.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.scoop.data.remote.model.Source

@Entity
data class LocalNews(
    @PrimaryKey(autoGenerate = true)
    val id:Int,
    val author: String?,
    val content: String?,
    val description: String?,
    val publishedAt: String?,
    val sourceId: String?,
    val category:String?,
    val sourceName: String?,
    val q:String?,
    val title: String,
    val url: String?,
    val urlToImage: String?
)
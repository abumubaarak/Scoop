package com.example.scoop.domain

data class News(
    val id:Int,
    val author: String?,
    val content: String?,
    val description: String?,
    val publishedAt: String?,
    val sourceId: String?,
    val sourceName: String?,
    val title: String,
    val url: String?,
    val urlToImage: String?
)
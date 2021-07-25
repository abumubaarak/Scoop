package com.example.scoop.data.remote.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class NewsResponse(
     val articles: List<Article>,
    val status: String,
    val totalResults: Int
){
 }
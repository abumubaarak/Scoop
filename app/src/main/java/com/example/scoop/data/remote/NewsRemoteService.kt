package com.example.scoop.data.remote

import com.example.scoop.data.remote.model.NewsResponse
 import retrofit2.http.GET
import retrofit2.http.Query

interface NewsRemoteService {

    @GET("top-headlines?country=ng&pageSize=20")
    suspend fun getHeadline(
        @Query("category") category: String
    ): NewsResponse

    @GET("everything?pageSize=7")
    suspend fun getEverything(
        @Query("q") q: String,
    ): NewsResponse


}

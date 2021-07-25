package com.example.scoop.data.local

import androidx.room.*
import com.example.scoop.data.local.model.LocalNews
import com.example.scoop.data.remote.model.Article
import kotlinx.coroutines.flow.Flow

@Dao
interface NewsLocalDataSource {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTopNews(news: List<LocalNews>)

    @Query("SELECT * FROM LocalNews WHERE category=:category")
    fun getTopHeadlineByCategory(category: String): Flow<List<LocalNews>>

    @Query("SELECT * FROM LocalNews WHERE  category='' ")
    fun getEverythingByQ(): Flow<List<LocalNews>>

    @Query("DELETE  FROM LocalNews WHERE category=''")
    suspend fun deleteEverything()

    @Query("DELETE  FROM LocalNews WHERE q=''")
    suspend fun deleteTopHeadline()

    @Transaction
    suspend fun updateTopHeadline(news: List<LocalNews>) {
        deleteTopHeadline()
        insertTopNews(news)
    }
    @Transaction
    suspend fun updateEverything(news: List<LocalNews>) {
        deleteEverything()
        insertTopNews(news)
    }
}
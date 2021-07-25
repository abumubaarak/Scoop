package com.example.scoop.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.scoop.data.local.model.LocalNews
import com.example.scoop.data.remote.model.Article

@Database(entities = [LocalNews::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun localNewsSource(): NewsLocalDataSource

}
package com.example.scoop.di

import android.app.Application
import androidx.room.Room
import com.example.scoop.data.local.AppDatabase
import com.example.scoop.data.local.NewsLocalDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object LocalModule{
    @Provides
    @Singleton
    fun provideRoomDatabase(application: Application): AppDatabase {
        return Room
            .databaseBuilder(application,AppDatabase::class.java,"Scoop.db")
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideDao(appDatabase: AppDatabase):NewsLocalDataSource{
        return appDatabase.localNewsSource()
    }
}
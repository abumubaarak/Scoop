package com.example.scoop.di

import android.content.Context
import com.example.scoop.BuildConfig
import com.example.scoop.data.remote.NewsRemoteService
 import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.*
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import timber.log.Timber
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {



    @Provides
    @Singleton
    fun provideOkhttp() : OkHttpClient {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        return OkHttpClient.Builder()
            .addNetworkInterceptor(Interceptor { chain ->
                var request: Request = chain.request()
                val url: HttpUrl =
                    request.url.newBuilder().addQueryParameter("apiKey", BuildConfig.API_KEY)
                        .build()
                request = request.newBuilder().url(url).build()
                 chain.proceed(request)
            })
            .build()

    }

    @Provides
    @Singleton
    fun provideMoshi():Moshi{
        return  Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient, moshi: Moshi): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(okHttpClient)
             .addConverterFactory( MoshiConverterFactory.create(moshi))
            .build()
    }


    @Provides
    @Singleton
    fun provideNewsRemote(retrofit: Retrofit):NewsRemoteService{
        return retrofit.create(NewsRemoteService::class.java)
    }



}
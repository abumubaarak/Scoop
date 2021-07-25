package com.example.scoop.repository

import com.dropbox.android.external.store4.*
import com.example.scoop.data.local.NewsLocalDataSource
import com.example.scoop.data.local.model.LocalNews
import com.example.scoop.data.mapper.toLocalNewsList
import com.example.scoop.data.mapper.toNewsList
import com.example.scoop.data.remote.NewsRemoteService
import com.example.scoop.data.remote.model.NewsResponse
import com.example.scoop.domain.News
import com.example.scoop.domain.Result
 import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.*
import timber.log.Timber
import javax.inject.Inject
import kotlin.time.ExperimentalTime

@ExperimentalCoroutinesApi
@FlowPreview
@ExperimentalTime
class MainRepository @Inject constructor(
    private val newsRemoteService: NewsRemoteService,
    private val localDataSource: NewsLocalDataSource
) {


    suspend fun getTopHeadline(category: String): Flow<Result<List<News>>> = flow {

        val store: Store<String, List<LocalNews>> = StoreBuilder.from(
            fetcher = Fetcher.of { _: String ->
                newsRemoteService.getHeadline(category)
            },
            sourceOfTruth = SourceOfTruth.Companion.of(
                reader = { _ -> localDataSource.getTopHeadlineByCategory(category) },
                writer = { _: String, data: NewsResponse ->
                    val lastest = data.toLocalNewsList(category, "")
                    localDataSource.updateTopHeadline(lastest)
                },
            )
        ).build()

        store.stream(StoreRequest.cached(key = "top_headline", refresh = true))
            .collect { response ->
                when (response) {
                    is StoreResponse.Loading -> {
                        emit(Result.loading<List<News>>())
                    }

                    is StoreResponse.Data -> {
                         emit(Result.success(response.value.toNewsList()))
                    }

                    is StoreResponse.Error -> {
                        emit(Result.error<List<News>>())
                     }

                    is StoreResponse.NoNewData -> {
                        emit(Result.success(emptyList<News>()))
                    }
                }
            }
    }.flowOn(Dispatchers.IO)


    suspend fun getEverything(): Flow<Result<List<News>>> = flow {

        val random = listOf(
            "computing",
            "internet",
            "plant",
            "Android",
            "Robot"
        ).shuffled()

        val searchQuery = random[(0..6).random()]

        val store: Store<String, List<LocalNews>> = StoreBuilder.from(

            fetcher = Fetcher.of { _: String ->
                newsRemoteService.getEverything(searchQuery)
            },
            sourceOfTruth = SourceOfTruth.Companion.of(
                reader = { _ -> localDataSource.getEverythingByQ() },
                writer = { _: String, data: NewsResponse ->
                    val lastest = data.toLocalNewsList("", searchQuery)
                    localDataSource.updateEverything(lastest)
                },
            )
        ).build()

        store.stream(StoreRequest.cached(key = "everything", refresh = true))
            .collect { response ->
                when (response) {
                    is StoreResponse.Loading -> {
                        emit(Result.loading<List<News>>())
                    }

                    is StoreResponse.Data -> {
                         emit(Result.success(response.value.toNewsList()))
                    }

                    is StoreResponse.Error -> {
                        emit(Result.error<List<News>>())
                     }

                    is StoreResponse.NoNewData -> {
                        emit(Result.success(emptyList<News>()))
                    }
                }
            }
    }.flowOn(Dispatchers.IO)

}

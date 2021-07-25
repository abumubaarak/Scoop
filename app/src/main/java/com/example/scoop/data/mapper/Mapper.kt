package com.example.scoop.data.mapper

import com.example.scoop.data.local.model.LocalNews
import com.example.scoop.data.remote.model.Article
import com.example.scoop.data.remote.model.NewsResponse
import com.example.scoop.domain.News
import timber.log.Timber

fun NewsResponse.toLocalNewsList(category:String,q:String):List<LocalNews>{
    return this.articles.map { it.toLocalNews(category,q) }
}

fun List<LocalNews>.toNewsList():List<News>{
    return this.map { it.toNews() }
}

fun Article.toLocalNews(category:String,q:String):LocalNews{
    with(this){
         return LocalNews(
            author = author,
            content = content,
            description = description,
            publishedAt = publishedAt,
            title = title,
            urlToImage = urlToImage,
            url = url,
             id = 0,
             q=q,
             category = category,
            sourceId = source?.id,
            sourceName = source?.name
        )

    }
}

fun LocalNews.toNews(): News {
    with(this){
        return News(
            author = author,
            content = content,
            description = description,
            publishedAt = publishedAt,
            title = title,
            urlToImage = urlToImage,
            url = url,
            id = 0,
            sourceId =sourceId,
            sourceName = sourceName
        )
    }
}
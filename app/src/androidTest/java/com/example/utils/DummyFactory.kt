package com.example.utils

import com.example.guardia.data_remote.model.news_api.ArticleSource
import com.example.guardia.data_remote.model.news_api.DomesticViolenceArticleResponse

object DummyFactory {
    val DUMMY_DOMESTIC_VIOLENCE_ARTICLE = DomesticViolenceArticleResponse(
        source = ArticleSource(
            sourceId = "id",
            sourceName = "name"
        ),
        author = "author",
        title = "title",
        description = "description",
        url = "url",
        urlToImage = "urlToImage",
        publishedAt = "publishedAt",
        content = "content"
    )
}
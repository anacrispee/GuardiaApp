package com.example.guardia.data_remote.model.news_api

import com.google.gson.annotations.SerializedName

data class WomansViolenceArticleResponse(
    @SerializedName("source")
    val source: ArticleSource? = ArticleSource(),
    @SerializedName("author")
    val author: String? = "",
    @SerializedName("title")
    val title: String? = "",
    @SerializedName("description")
    val description: String? = "",
    @SerializedName("url")
    val url: String? = "",
    @SerializedName("urlToImage")
    val urlToImage: String? = "",
    @SerializedName("publishedAt")
    val publishedAt: String? = "",
    @SerializedName("content")
    val content: String? = "",
)

data class ArticleSource(
    @SerializedName("id")
    val sourceId: String? = "",
    @SerializedName("name")
    val sourceName: String? = ""
)
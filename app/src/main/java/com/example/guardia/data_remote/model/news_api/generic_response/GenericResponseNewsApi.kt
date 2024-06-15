package com.example.guardia.data_remote.model.news_api.generic_response

import com.google.gson.annotations.SerializedName

data class GenericResponseNewsApi<T>(
    @SerializedName("status")
    val status: String,
    @SerializedName("totalResults")
    val totalResults: Int,
    @SerializedName("articles")
    val articles: T?
)
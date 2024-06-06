package com.example.guardia.features.feature_home

import com.example.guardia.data_remote.model.news_api.WomansViolenceArticleResponse

data class HomeViewState(
    var isLoading: Boolean = false,
    var error: Throwable? = null,
    val searchInputValue: String = "",
    val violenceArticles: List<WomansViolenceArticleResponse>? = null
)
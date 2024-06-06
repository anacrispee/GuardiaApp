package com.example.guardia.features.feature_home

import com.example.guardia.data_remote.model.news_api.DomesticViolenceArticleResponse

data class HomeViewState(
    var isLoading: Boolean = false,
    var error: Throwable? = null,
    val searchInputValue: String = "",
    val domesticViolencePopularArticles: List<DomesticViolenceArticleResponse>? = null,
    val domesticViolenceStories: List<DomesticViolenceArticleResponse>? = null
)
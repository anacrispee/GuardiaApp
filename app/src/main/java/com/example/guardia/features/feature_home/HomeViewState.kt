package com.example.guardia.features.feature_home

import com.example.guardia.data_remote.model.news_api.DomesticViolenceArticleResponse

data class HomeViewState(
    var isLoading: Boolean = false,
    var error: Throwable? = null,
    val filterId: Int = FiltersEnum.VIOLENCE.id,
    val domesticViolencePopularArticles: List<DomesticViolenceArticleResponse>? = null,
    val domesticViolenceStories: List<DomesticViolenceArticleResponse>? = null,
    val domesticPsychologicalAbuseArticles: List<DomesticViolenceArticleResponse>? = null,
    val harassmentAgainstWomenArticles: List<DomesticViolenceArticleResponse>? = null,
    val threatAgainstWomenArticles: List<DomesticViolenceArticleResponse>? = null,
    val isEmptyState: Boolean = false,
    val hasSearched: Boolean = false,
    val searchId: Int = FiltersEnum.VIOLENCE.id,
)
package com.example.guardia.features.feature_home

sealed class HomeViewAction {
    data class FetchDataByFilterOption(val id: Int) : HomeViewAction()
    data class SearchNewsSubject(val subject: String) : HomeViewAction()
    data class UpdateIsEmpty(val value: Boolean) : HomeViewAction()

    object GetDomesticViolenceArticles : HomeViewAction()
    object GetDomesticViolenceStories : HomeViewAction()
}
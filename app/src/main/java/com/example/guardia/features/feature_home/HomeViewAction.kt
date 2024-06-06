package com.example.guardia.features.feature_home

sealed class HomeViewAction {
    object GetDomesticViolenceArticles : HomeViewAction()
    object GetDomesticViolenceStories : HomeViewAction()

    data class ChangeInputValue(val value: String) : HomeViewAction()
    data class FetchDataByFilterOption(val id: Int) : HomeViewAction()
}
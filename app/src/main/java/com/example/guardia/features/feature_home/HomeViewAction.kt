package com.example.guardia.features.feature_home

sealed class HomeViewAction {
    object GetWomanViolenceArticles : HomeViewAction()
    data class ChangeInputValue(val value: String) : HomeViewAction()
}
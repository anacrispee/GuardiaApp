package com.example.guardia.features.feature_home

data class HomeViewState(
    var isLoading: Boolean = false,
    var error: Throwable? = null,
    val searchInputValue: String = "",
)
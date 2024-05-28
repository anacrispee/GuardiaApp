package com.example.guardia.features.feature_home

data class HomeScreenViewState(
    var isLoading: Boolean = false,
    var error: Throwable? = null
)
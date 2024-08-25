package com.example.guardia.features.feature_my_profile

data class MyProfileViewState(
    val isLoading: Boolean = false,
    val error: Throwable? =null
)
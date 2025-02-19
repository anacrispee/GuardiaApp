package com.example.guardia.features.feature_my_profile

import com.example.guardia.data_remote.model.user.UserModel

data class MyProfileViewState(
    val isLoading: Boolean = false,
    val error: Throwable? = null,
    val user: UserModel? = null
)
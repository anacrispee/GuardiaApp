package com.example.guardia.features.feature_my_profile

import com.example.guardia.domain.models.user.UserModel

data class MyProfileViewState(
    val isLoading: Boolean = false,
    val error: Throwable? = null,
    val user: UserModel? = null,
    val showConfirmDeleteAccountBottomSheet: Boolean = false
)
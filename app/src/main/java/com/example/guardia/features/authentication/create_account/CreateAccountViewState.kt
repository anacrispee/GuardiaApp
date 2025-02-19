package com.example.guardia.features.authentication.create_account

data class CreateAccountViewState(
    val isLoading: Boolean = false,
    val error: Throwable? = null,
    val name: String = "",
    val email: String = "",
    val password: String = "",
    val confirmPassword: String = "",
    val isPasswordHide: Boolean = true,
    val isPasswordConfirmationHide: Boolean = true,
    val passwordsMatch: Boolean? = null
)
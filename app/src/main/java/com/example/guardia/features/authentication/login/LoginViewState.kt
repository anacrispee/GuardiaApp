package com.example.guardia.features.authentication.login

data class LoginViewState(
    val isLoading: Boolean = false,
    val error: Throwable? = null,
    val emailInput: String = "",
    val passwordInput: String = "",
    val isPasswordHide: Boolean = true,
    val hasLogged: Boolean = false,
    val loginError: Throwable? = null
)
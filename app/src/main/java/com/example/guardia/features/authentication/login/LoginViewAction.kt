package com.example.guardia.features.authentication.login

sealed class LoginViewAction {
    data class UpdateEmailInput(val value: String) : LoginViewAction()
    data class UpdatePasswordInput(val value: String) : LoginViewAction()

    object ShowPassword : LoginViewAction()
    object Login : LoginViewAction()
}
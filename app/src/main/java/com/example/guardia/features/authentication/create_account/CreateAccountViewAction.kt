package com.example.guardia.features.authentication.create_account

sealed class CreateAccountViewAction {
    data class UpdateNameInput(val value: String) : CreateAccountViewAction()
    data class UpdateEmailInput(val value: String) : CreateAccountViewAction()
    data class UpdatePasswordInput(val value: String) : CreateAccountViewAction()
    data class UpdatePasswordConfirmationInput(val value: String) : CreateAccountViewAction()

    object CreateAccount : CreateAccountViewAction()
    object ShowPassword : CreateAccountViewAction()
    object ShowPasswordConfirmation : CreateAccountViewAction()
}
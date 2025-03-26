package com.example.guardia.features.authentication.create_account

import androidx.navigation.NavController

sealed class CreateAccountViewAction {
    data class UpdateNameInput(val value: String) : CreateAccountViewAction()
    data class UpdateEmailInput(val value: String) : CreateAccountViewAction()
    data class UpdatePasswordInput(val value: String) : CreateAccountViewAction()
    data class UpdatePasswordConfirmationInput(val value: String) : CreateAccountViewAction()
    data class CreateAccount(val navController: NavController) : CreateAccountViewAction()

    object ShowPassword : CreateAccountViewAction()
    object ShowPasswordConfirmation : CreateAccountViewAction()
}
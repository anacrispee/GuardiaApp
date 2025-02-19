package com.example.guardia.features.authentication.create_account

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.auth.userProfileChangeRequest
import org.koin.core.component.KoinComponent

class CreateAccountViewModel : ViewModel(), KoinComponent {
    var viewState by mutableStateOf(CreateAccountViewState())
    private val auth = Firebase.auth

    fun dispatcherViewAction(action: CreateAccountViewAction) {
        when (action) {
            is CreateAccountViewAction.UpdateNameInput -> viewState = viewState.copy(
                name = action.value
            )
            is CreateAccountViewAction.UpdateEmailInput -> viewState = viewState.copy(
                email = action.value
            )
            is CreateAccountViewAction.UpdatePasswordInput -> viewState = viewState.copy(
                password = action.value
            )
            is CreateAccountViewAction.UpdatePasswordConfirmationInput -> viewState = viewState.copy(
                confirmPassword = action.value,
                passwordsMatch = if (viewState.password.isBlank()) true else viewState.password == action.value
            )

            CreateAccountViewAction.CreateAccount -> createAccount()
            CreateAccountViewAction.ShowPassword -> viewState = viewState.copy(
                isPasswordHide = viewState.isPasswordHide.not()
            )
            CreateAccountViewAction.ShowPasswordConfirmation -> viewState = viewState.copy(
                isPasswordConfirmationHide = viewState.isPasswordConfirmationHide.not()
            )
        }
    }

    private fun createAccount() {
        auth.createUserWithEmailAndPassword(
            viewState.email,
            viewState.password
        )
        updateCurrentUserName()
    }

    private fun updateCurrentUserName() {
        val user = auth.currentUser
        val profileUpdates = userProfileChangeRequest {
            displayName = viewState.name
        }
        user?.updateProfile(profileUpdates)
    }
}
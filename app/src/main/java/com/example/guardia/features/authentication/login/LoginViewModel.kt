package com.example.guardia.features.authentication.login

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import org.koin.core.component.KoinComponent

class LoginViewModel : ViewModel(), KoinComponent {
    var viewState by mutableStateOf(LoginViewState())

    fun dispatcherViewAction(action: LoginViewAction) {
        when (action) {
            is LoginViewAction.UpdateEmailInput -> {
                viewState = viewState.copy(
                    emailInput = action.value,
                    loginError = null
                )
            }
            is LoginViewAction.UpdatePasswordInput -> {
                viewState = viewState.copy(
                    passwordInput = action.value,
                    loginError = null
                )
            }

            LoginViewAction.ShowPassword -> {
                viewState = viewState.copy(isPasswordHide = viewState.isPasswordHide.not())
            }
            LoginViewAction.Login -> login()
        }
    }

    private fun login() {
        val auth = Firebase.auth

        viewState = viewState.copy(
            isLoading = true
        )
        auth.signInWithEmailAndPassword(
            viewState.emailInput,
            viewState.passwordInput
        )
            .addOnCompleteListener { task ->
                viewState = if (task.isSuccessful) {
                    viewState.copy(
                        isLoading = false,
                        hasLogged = true,
                        loginError = null
                    )
                } else {
                    viewState.copy(
                        isLoading = false,
                        hasLogged = false,
                        loginError = task.exception
                    )
                }
            }
    }
}
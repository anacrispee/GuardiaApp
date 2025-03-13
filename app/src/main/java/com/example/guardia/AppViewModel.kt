package com.example.guardia

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.guardia.domain.use_case.authentication.GetUserUseCase
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.core.parameter.parametersOf

class AppViewModel : ViewModel(), KoinComponent {
    val getUserUseCase: GetUserUseCase by inject { parametersOf(viewModelScope) }

    fun teti() {
        getUserUseCase(
            onSuccess = { currentUser ->
                println("dkjflsdkjf - batata frita com ketchup currentUser = $currentUser")
//                hasUserLogged = currentUser != null
            },
            onError = {
                println("dkjflsdkjf - error = $it")
                println("dkjflsdkjf - error message = ${it.message}")
            }
        )
    }
}
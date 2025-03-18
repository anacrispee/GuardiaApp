package com.example.guardia

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.guardia.domain.models.firebase.UserAuthModel
import com.example.guardia.domain.use_case.authentication.GetUserUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.core.parameter.parametersOf

class AppViewModel : ViewModel(), KoinComponent {
    val getUserUseCase: GetUserUseCase by inject { parametersOf(viewModelScope) }

    private val _user = MutableStateFlow(UserAuthModel())
    val user = _user.asStateFlow()

    fun getUser() {
        getUserUseCase(
            onSuccess = { currentUser ->
                _user.update {
                    it.copy(
                        user = currentUser
                    )
                }
            },
            onError = { _ ->
                _user.update {
                    it.copy(
                        user = null
                    )
                }
            }
        )
    }
}
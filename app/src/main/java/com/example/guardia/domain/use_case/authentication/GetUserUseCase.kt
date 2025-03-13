package com.example.guardia.domain.use_case.authentication

import com.example.guardia.domain.core.UseCase
import com.example.guardia.domain.repository.AuthenticationRepository
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class GetUserUseCase(
    scope: CoroutineScope,
    private val repository: AuthenticationRepository
) : UseCase<FirebaseUser?, Unit>(scope) {

    override fun run(params: Unit?): Flow<FirebaseUser?> = flowOf(
        repository.getUser()
    )
}
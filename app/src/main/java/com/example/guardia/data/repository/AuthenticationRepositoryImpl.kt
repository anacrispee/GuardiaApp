package com.example.guardia.data.repository

import com.example.guardia.data.remote.firebase.authentication.AuthenticationDataSource
import com.example.guardia.domain.repository.AuthenticationRepository
import com.google.firebase.auth.FirebaseUser

class AuthenticationRepositoryImpl(
    private val dataSource: AuthenticationDataSource
) : AuthenticationRepository {

    override fun getUser(): FirebaseUser? =
        dataSource.getUser()
}
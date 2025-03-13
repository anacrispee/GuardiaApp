package com.example.guardia.data_remote.datasource

import com.example.guardia.data.remote.firebase.authentication.AuthenticationDataSource
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.auth
import org.koin.core.component.KoinComponent

class AuthenticationDataSourceImpl(
    private val service: FirebaseAuth,
) : AuthenticationDataSource, KoinComponent {

    override fun getUser(): FirebaseUser? =
        service.currentUser

    override fun signIn(email: String, password: String) {
        TODO("Not yet implemented")
    }

    override fun signOut() {
        TODO("Not yet implemented")
    }

    override fun createAccount(email: String, password: String, name: String) {
        TODO("Not yet implemented")
    }

    override fun deleteAccount() {
        TODO("Not yet implemented")
    }
}
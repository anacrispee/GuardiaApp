package com.example.guardia.data.remote.firebase.authentication

import com.google.firebase.auth.FirebaseUser

interface AuthenticationDataSource {
    fun getUser() : FirebaseUser?
    fun signIn(email: String, password: String)
    fun signOut()
    fun createAccount(email: String, password: String, name: String)
    fun deleteAccount()
}
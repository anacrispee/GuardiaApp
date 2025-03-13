package com.example.guardia.domain.repository

import com.google.firebase.auth.FirebaseUser

interface AuthenticationRepository {
    fun getUser() : FirebaseUser?
}
package com.example.guardia.domain.models.firebase

import com.google.firebase.auth.FirebaseUser

data class UserAuthModel(
    val user: FirebaseUser? = null
)
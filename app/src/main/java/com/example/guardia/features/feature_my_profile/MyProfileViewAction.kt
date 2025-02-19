package com.example.guardia.features.feature_my_profile

import androidx.navigation.NavHostController

sealed class MyProfileViewAction {
    data class Logout(val navController: NavHostController) : MyProfileViewAction()
    object GetUser : MyProfileViewAction()
}
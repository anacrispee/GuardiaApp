package com.example.guardia.di

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.guardia.AppConstants
import com.example.guardia.features.feature_home.HomeScreen
import com.example.guardia.features.feature_my_profile.MyProfileScreen
import com.example.guardia.features.feature_panic_button.PanicButtonScreen
import com.example.guardia.features.feature_shelters.FindSheltersScreen

@Composable
fun NavigationGraph(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = AppConstants.HOME_SCREEN
    ) {
        composable(AppConstants.HOME_SCREEN) {
            HomeScreen(navController)
        }

        composable(AppConstants.MY_PROFILE_SCREEN) {
            MyProfileScreen(navController)
        }

        composable(AppConstants.PANIC_BUTTON_SCREEN) {
            PanicButtonScreen(navController)
        }

        composable(AppConstants.FIND_SHELTERS_SCREEN) {
            FindSheltersScreen(navController)
        }
    }
}
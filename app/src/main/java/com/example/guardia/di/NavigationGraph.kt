package com.example.guardia.di

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.guardia.AppConstants.ARTICLE_READING_SCREEN
import com.example.guardia.AppConstants.FIND_SHELTERS_SCREEN
import com.example.guardia.AppConstants.HOME_SCREEN
import com.example.guardia.AppConstants.MY_PROFILE_SCREEN
import com.example.guardia.AppConstants.PANIC_BUTTON_SCREEN
import com.example.guardia.features.article_reading_screen.ArticleReadingScreen
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
        startDestination = HOME_SCREEN
    ) {
        composable(HOME_SCREEN) {
            HomeScreen(navController)
        }

        composable(MY_PROFILE_SCREEN) {
            MyProfileScreen(navController)
        }

        composable(PANIC_BUTTON_SCREEN) {
            PanicButtonScreen(navController)
        }

        composable(FIND_SHELTERS_SCREEN) {
            FindSheltersScreen(navController)
        }

        composable(route = CONNECTION_ERROR_SCREEN_ARGS,
            arguments = listOf(navArgument("fromScreen") {
                type = NavType.StringType
            })) {
            it.arguments?.getString("fromScreen")?.let { it1 ->
                ConnectionErrorScreen(
                    navController = navController,
                    fromScreen = it1
                )
            }
        }

        composable(ARTICLE_READING_SCREEN) {
            ArticleReadingScreen(navController)
        }
    }
}

object NavGraphConstants {
    const val HOME_SCREEN = "HomeScreen"
    const val MY_PROFILE_SCREEN = "MyProfileScreen"
    const val PANIC_BUTTON_SCREEN = "PanicButtonScreen"
    const val FIND_SHELTERS_SCREEN = "FindSheltersScreen"

    const val CONNECTION_ERROR_SCREEN_ARGS = "ConnectionErrorScreen/{fromScreen}"
}
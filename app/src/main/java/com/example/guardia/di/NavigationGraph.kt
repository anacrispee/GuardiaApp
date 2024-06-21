package com.example.guardia.di

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.guardia.di.NavGraphConstants.CONNECTION_ERROR_SCREEN_ARGS
import com.example.guardia.di.NavGraphConstants.FIND_SHELTERS_SCREEN
import com.example.guardia.di.NavGraphConstants.HOME_SCREEN
import com.example.guardia.di.NavGraphConstants.MY_PROFILE_SCREEN
import com.example.guardia.di.NavGraphConstants.PANIC_BUTTON_SCREEN
import com.example.guardia.features.connection_error_screen.ConnectionErrorScreen
import com.example.guardia.features.feature_home.HomeScreen
import com.example.guardia.features.feature_my_profile.MyProfileScreen
import com.example.guardia.features.feature_panic_button.PanicButtonScreen
import com.example.guardia.features.feature_shelters.FindSheltersScreen

@Composable
fun NavigationGraph(
    navController: NavHostController
) {
    NavHost(
        navController = navController, startDestination = HOME_SCREEN
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
    }
}

object NavGraphConstants {
    const val HOME_SCREEN = "HomeScreen"
    const val MY_PROFILE_SCREEN = "MyProfileScreen"
    const val PANIC_BUTTON_SCREEN = "PanicButtonScreen"
    const val FIND_SHELTERS_SCREEN = "FindSheltersScreen"

    const val CONNECTION_ERROR_SCREEN_ARGS = "ConnectionErrorScreen/{fromScreen}"
}
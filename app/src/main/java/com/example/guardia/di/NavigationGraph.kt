package com.example.guardia.di

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.guardia.di.NavGraphConstants.ARTICLE_READING_SCREEN_ARGS
import com.example.guardia.di.NavGraphConstants.CONNECTION_ERROR_SCREEN
import com.example.guardia.di.NavGraphConstants.FIND_SHELTERS_SCREEN
import com.example.guardia.di.NavGraphConstants.HOME_SCREEN
import com.example.guardia.di.NavGraphConstants.LOGIN_SCREEN
import com.example.guardia.di.NavGraphConstants.MY_PROFILE_SCREEN
import com.example.guardia.di.NavGraphConstants.PANIC_BUTTON_SCREEN
import com.example.guardia.features.article_reading_screen.ArticleReadingScreen
import com.example.guardia.features.authentication.login.LoginScreen
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
        navController = navController,
        startDestination = LOGIN_SCREEN
    ) {
        //region home screen
        composable(HOME_SCREEN) {
            HomeScreen(navController)
        }

        composable(ARTICLE_READING_SCREEN_ARGS,
            arguments = listOf(
                navArgument("title") { type = NavType.StringType },
                navArgument("author") { type = NavType.StringType },
                navArgument("publishedAt") { type = NavType.StringType },
                navArgument("contentLink") { type = NavType.StringType }
            )
        ) {
            val title = it.arguments?.getString("title")
            val author = it.arguments?.getString("author")
            val publishedAt = it.arguments?.getString("publishedAt")
            val contentLink = it.arguments?.getString("contentLink")

            ArticleReadingScreen(
                navController = navController,
                title = title.orEmpty(),
                author = author.orEmpty(),
                publishedAt = publishedAt.orEmpty(),
                contentLink = contentLink.orEmpty()
            )
        }
        //endregion

        //region authentication
        composable(LOGIN_SCREEN) {
            LoginScreen(navController)
        }
        //endregion

        //region others
        composable(MY_PROFILE_SCREEN) {
            MyProfileScreen(navController)
        }

        composable(PANIC_BUTTON_SCREEN) {
            PanicButtonScreen(navController)
        }

        composable(FIND_SHELTERS_SCREEN) {
            FindSheltersScreen(navController)
        }

        composable(route = CONNECTION_ERROR_SCREEN) {
                ConnectionErrorScreen(
                    navController = navController
                )
            }
        //endregion
    }
}

object NavGraphConstants {
    //region home screen
    const val HOME_SCREEN = "HomeScreen"
    const val ARTICLE_READING_SCREEN = "ArticleReadingScreen"
    const val ARTICLE_READING_SCREEN_ARGS =
        "$ARTICLE_READING_SCREEN/{title}/{author}/{publishedAt}/{contentLink}"
    //endregion

    //region authentication
    const val LOGIN_SCREEN = "LoginScreen"
    //endregion

    //region others
    const val MY_PROFILE_SCREEN = "MyProfileScreen"
    const val PANIC_BUTTON_SCREEN = "PanicButtonScreen"
    const val FIND_SHELTERS_SCREEN = "FindSheltersScreen"
    const val CONNECTION_ERROR_SCREEN = "ConnectionErrorScreen"
    //endregion
}
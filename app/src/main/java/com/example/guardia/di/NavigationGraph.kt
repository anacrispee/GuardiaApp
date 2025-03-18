package com.example.guardia.di

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.guardia.AppViewModel
import com.example.guardia.di.NavGraphConstants.ARTICLE_READING_SCREEN_ARGS
import com.example.guardia.di.NavGraphConstants.CONNECTION_ERROR_SCREEN
import com.example.guardia.di.NavGraphConstants.CREATE_ACCOUNT_SCREEN
import com.example.guardia.di.NavGraphConstants.FIND_SHELTERS_SCREEN
import com.example.guardia.di.NavGraphConstants.HOME_SCREEN
import com.example.guardia.di.NavGraphConstants.LOGIN_SCREEN
import com.example.guardia.di.NavGraphConstants.MY_PROFILE_SCREEN
import com.example.guardia.di.NavGraphConstants.PANIC_BUTTON_SCREEN
import com.example.guardia.features.article_reading_screen.ArticleReadingScreen
import com.example.guardia.features.authentication.create_account.CreateAccountScreen
import com.example.guardia.features.authentication.login.LoginScreen
import com.example.guardia.features.connection_error_screen.ConnectionErrorScreen
import com.example.guardia.features.feature_home.HomeScreen
import com.example.guardia.features.feature_my_profile.MyProfileScreen
import com.example.guardia.features.feature_panic_button.PanicButtonScreen
import com.example.guardia.features.feature_shelters.FindSheltersScreen
import org.koin.androidx.compose.koinViewModel

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
                navArgument(NavArguments.TITLE) { type = NavType.StringType },
                navArgument(NavArguments.AUTHOR) { type = NavType.StringType },
                navArgument(NavArguments.PUBLISHED_AT) { type = NavType.StringType },
                navArgument(NavArguments.CONTENT_LINK) { type = NavType.StringType }
            )
        ) {
            val title = it.arguments?.getString(NavArguments.TITLE)
            val author = it.arguments?.getString(NavArguments.AUTHOR)
            val publishedAt = it.arguments?.getString(NavArguments.PUBLISHED_AT)
            val contentLink = it.arguments?.getString(NavArguments.CONTENT_LINK)

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
            val viewModel: AppViewModel = koinViewModel()
            val currentUser by viewModel.user.collectAsStateWithLifecycle()
            LaunchedEffect(true) {
                if (currentUser.user != null) {
                    navController.navigate(HOME_SCREEN)
                }
            }
            LoginScreen(navController)
        }

        composable(CREATE_ACCOUNT_SCREEN) {
            CreateAccountScreen(navController)
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
    const val CREATE_ACCOUNT_SCREEN = "CreateAccountScreen"
    //endregion

    //region others
    const val MY_PROFILE_SCREEN = "MyProfileScreen"
    const val PANIC_BUTTON_SCREEN = "PanicButtonScreen"
    const val FIND_SHELTERS_SCREEN = "FindSheltersScreen"
    const val CONNECTION_ERROR_SCREEN = "ConnectionErrorScreen"
    //endregion
}

object NavArguments {
    const val TITLE = "title"
    const val AUTHOR = "author"
    const val PUBLISHED_AT = "publishedAt"
    const val CONTENT_LINK = "contentLink"
}
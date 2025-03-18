package com.example.guardia

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.FiniteAnimationSpec
import androidx.compose.animation.core.spring
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemColors
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.guardia.di.NavGraphConstants.CREATE_ACCOUNT_SCREEN
import com.example.guardia.di.NavGraphConstants.FIND_SHELTERS_SCREEN
import com.example.guardia.di.NavGraphConstants.HOME_SCREEN
import com.example.guardia.di.NavGraphConstants.LOGIN_SCREEN
import com.example.guardia.di.NavGraphConstants.MY_PROFILE_SCREEN
import com.example.guardia.di.NavGraphConstants.PANIC_BUTTON_SCREEN
import com.example.guardia.di.NavigationGraph
import com.example.guardia.ui.app_theme.AppTheme
import org.koin.android.ext.android.inject

class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        setContent {
            val viewModel: AppViewModel by inject()
            val navController = rememberNavController()
            val items = bottomNavigationItems()
            val animationSpec : FiniteAnimationSpec<Float> = spring(
                dampingRatio = 0.3f,
                stiffness = 100f
            )
            val currentRoute = currentRoute(navController)

            LaunchedEffect(true) {
                viewModel.getUser()
            }

            Surface(
                modifier = Modifier.fillMaxSize()
            ) {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    containerColor = AppTheme.colors.secondary.background,
                    bottomBar = {
                        AnimatedVisibility(
                            visible = (currentRoute != LOGIN_SCREEN) && (currentRoute != CREATE_ACCOUNT_SCREEN),
                            enter = fadeIn(animationSpec),
                            exit = fadeOut(animationSpec)
                        ) {
                            val navStackBackEntry by navController.currentBackStackEntryAsState()
                            val currentDestination = navStackBackEntry?.destination

                            NavigationBar(
                                containerColor = AppTheme.colors.primary.dark_pink
                            ) {
                                items.map { item ->
                                    val color = AppTheme.colors.secondary.lighter
                                    val isSelected = currentDestination?.hierarchy?.any { it.route == item.route}

                                    NavigationBarItem(
                                        colors = NavigationBarItemColors(
                                            disabledIconColor = color,
                                            selectedIconColor = color,
                                            selectedTextColor = color,
                                            disabledTextColor = color,
                                            selectedIndicatorColor = AppTheme.colors.primary.darker_pink,
                                            unselectedIconColor = color,
                                            unselectedTextColor = color
                                        ),
                                        selected = isSelected ?: false,
                                        onClick = {
                                            navController.navigate(item.route)
                                        },
                                        label = {
                                            Text(text = item.title)
                                        },
                                        icon = {
                                            BadgedBox(
                                                badge = {
                                                    if (item.badgeCount != null)
                                                        Badge(
                                                            containerColor = color,
                                                            contentColor = AppTheme.colors.primary.dark_pink
                                                        ) {
                                                            Text(text = item.badgeCount.toString())
                                                        }
                                                    else if (item.hasNews)
                                                        Badge(
                                                            containerColor = color
                                                        )
                                                }
                                            ) {
                                                Image(
                                                    modifier = Modifier
                                                        .size(24.dp),
                                                    painter = if (isSelected == true)
                                                        item.selectedIcon
                                                    else
                                                        item.unselectedIcon,
                                                    contentDescription = item.title
                                                )
                                            }
                                        }
                                    )
                                }
                            }
                        }
                    }
                ) { innerPadding ->
                    Box(
                        modifier = Modifier
                            .padding(innerPadding)
                    ) {
                        NavigationGraph(
                            navController = navController
                        )
                    }
                }
            }
        }
    }

    @Composable
    fun currentRoute(navController: NavController): String? {
        val backStackEntry = navController.currentBackStackEntryAsState()
        return backStackEntry.value?.destination?.route
    }

    @Composable
    private fun bottomNavigationItems(): List<BottomNavigationItem> {
        val items = listOf(
            BottomNavigationItem(
                route = HOME_SCREEN,
                title = stringResource(id = R.string.navigation_bar_home),
                selectedIcon = painterResource(id = R.drawable.ic_home_selected),
                unselectedIcon = painterResource(id = R.drawable.ic_home)
            ),
            BottomNavigationItem(
                route = PANIC_BUTTON_SCREEN,
                title = stringResource(id = R.string.navigation_bar_emergency),
                selectedIcon = painterResource(id = R.drawable.ic_call_selected),
                unselectedIcon = painterResource(id = R.drawable.ic_call)
            ),
            BottomNavigationItem(
                route = FIND_SHELTERS_SCREEN,
                title = stringResource(id = R.string.navigation_bar_find_shelters),
                selectedIcon = painterResource(id = R.drawable.ic_tent_selected),
                unselectedIcon = painterResource(id = R.drawable.ic_tent)
            ),
            BottomNavigationItem(
                route = MY_PROFILE_SCREEN,
                title = stringResource(id = R.string.navigation_bar_my_profile),
                selectedIcon = painterResource(id = R.drawable.ic_user_selected),
                unselectedIcon = painterResource(id = R.drawable.ic_user)
            )
        )
        return items
    }

    data class BottomNavigationItem(
        val route: String,
        val title: String,
        val selectedIcon: Painter,
        val unselectedIcon: Painter,
        val hasNews: Boolean = false,
        val badgeCount: Int? = null
    )
}
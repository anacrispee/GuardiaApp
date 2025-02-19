package com.example.guardia

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.rememberNavController
import com.example.guardia.di.NavGraphConstants.FIND_SHELTERS_SCREEN
import com.example.guardia.di.NavGraphConstants.HOME_SCREEN
import com.example.guardia.di.NavGraphConstants.MY_PROFILE_SCREEN
import com.example.guardia.di.NavGraphConstants.PANIC_BUTTON_SCREEN
import com.example.guardia.di.NavigationGraph
import com.example.guardia.ui.app_theme.AppTheme
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        setContent {
            var selectedItemIndex by rememberSaveable {
                mutableIntStateOf(0)
            }
            val navController = rememberNavController()
            val items = bottomNavigationItems()
            var hasUserLogged by remember { mutableStateOf(Firebase.auth.currentUser != null) }

            Firebase.auth.addAuthStateListener { auth ->
                hasUserLogged = auth.currentUser != null
            }
            println("sdlkfjlsdkjf - bateu Activity")
            println("sdlkfjlsdkjf - hasUserLogged: $hasUserLogged")

            Surface(
                modifier = Modifier.fillMaxSize()
            ) {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    containerColor = AppTheme.colors.secondary.background,
                    bottomBar = {
                        if (hasUserLogged) {
                            NavigationBar(
                                containerColor = AppTheme.colors.primary.dark_pink
                            ) {
                                items.forEachIndexed { index, item ->
                                    val color = AppTheme.colors.secondary.lighter
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
                                        selected = selectedItemIndex == index,
                                        onClick = {
                                            selectedItemIndex = index
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
                                                    painter = if (index == selectedItemIndex)
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
                            navController = navController,
                            hasUserLogged = hasUserLogged
                        )
                    }
                }
            }
        }
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
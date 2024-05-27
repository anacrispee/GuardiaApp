package com.example.guardia

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Place
import androidx.compose.material.icons.outlined.Call
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Place
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemColors
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.guardia.AppConstants.FIND_SHELTERS_SCREEN
import com.example.guardia.AppConstants.HOME_SCREEN
import com.example.guardia.AppConstants.MY_PROFILE_SCREEN
import com.example.guardia.AppConstants.PANIC_BUTTON_SCREEN
import com.example.guardia.features.feature_home.HomeScreen
import com.example.guardia.features.feature_my_profile.MyProfileScreen
import com.example.guardia.features.feature_panic_button.PanicButtonScreen
import com.example.guardia.features.feature_shelters.FindSheltersScreen
import com.example.guardia.ui.app_theme.AppTheme

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

            Surface(
                modifier = Modifier.fillMaxSize(),
                color = AppTheme.colors.secondary.background
            ) {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    bottomBar = {
                        NavigationBar(
                            containerColor = AppTheme.colors.primary.dark_pink,
                            modifier = Modifier
                                .clip(RoundedCornerShape(topStart = 14.dp, topEnd = 14.dp))
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
                                            Icon(
                                                imageVector = if (index == selectedItemIndex)
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
                ) {
                    NavigationGraph(
                        navController = navController
                    )
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
                selectedIcon = Icons.Default.Home,
                unselectedIcon = Icons.Outlined.Home,
                badgeCount = 2
            ),
            BottomNavigationItem(
                route = PANIC_BUTTON_SCREEN,
                title = stringResource(id = R.string.navigation_bar_emergency),
                selectedIcon = Icons.Default.Call,
                unselectedIcon = Icons.Outlined.Call
            ),
            BottomNavigationItem(
                route = FIND_SHELTERS_SCREEN,
                title = stringResource(id = R.string.navigation_bar_find_shelters),
                selectedIcon = Icons.Default.Place,
                unselectedIcon = Icons.Outlined.Place,
                hasNews = true
            ),
            BottomNavigationItem(
                route = MY_PROFILE_SCREEN,
                title = stringResource(id = R.string.navigation_bar_my_profile),
                selectedIcon = Icons.Default.Person,
                unselectedIcon = Icons.Outlined.Person
            )
        )
        return items
    }

    data class BottomNavigationItem(
        val route: String,
        val title: String,
        val selectedIcon: ImageVector,
        val unselectedIcon: ImageVector,
        val hasNews: Boolean = false,
        val badgeCount: Int? = null
    )

    @Composable
    fun NavigationGraph(navController: NavHostController) {
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
        }
    }
}
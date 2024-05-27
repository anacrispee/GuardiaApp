package com.example.guardia

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
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
                        NavigationBar {
                            items.forEachIndexed { index, item ->
                                NavigationBarItem(
                                    selected = selectedItemIndex == index,
                                    onClick = {
                                        selectedItemIndex = index
                                        navController.navigate(item.title)
                                    },
                                    label = {
                                        Text(text = item.title)
                                    },
                                    icon = {
                                        BadgedBox(
                                            badge = {
                                                if (item.badgeCount != null)
                                                    Badge {
                                                        Text(text = item.badgeCount.toString())
                                                    }
                                                else if (item.hasNews)
                                                    Badge()
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
                title = HOME_SCREEN,
                selectedIcon = Icons.Default.Home,
                unselectedIcon = Icons.Outlined.Home,
                hasNews = false
            ),
            BottomNavigationItem(
                title = MY_PROFILE_SCREEN,
                selectedIcon = Icons.Default.Email,
                unselectedIcon = Icons.Outlined.Email,
                hasNews = false,
                badgeCount = 45
            ),
            BottomNavigationItem(
                title = PANIC_BUTTON_SCREEN,
                selectedIcon = Icons.Default.Settings,
                unselectedIcon = Icons.Outlined.Settings,
                hasNews = true
            ),
            BottomNavigationItem(
                title = FIND_SHELTERS_SCREEN,
                selectedIcon = Icons.Default.Settings,
                unselectedIcon = Icons.Outlined.Settings,
                hasNews = true
            )
        )
        return items
    }

    data class BottomNavigationItem(
        val title: String,
        val selectedIcon: ImageVector,
        val unselectedIcon: ImageVector,
        val hasNews: Boolean,
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
package com.gyleedev.clonestagram.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.gyleedev.clonestagram.R
import com.gyleedev.clonestagram.ui.detail.DetailScreen
import com.gyleedev.clonestagram.ui.detail.ReelsScreen
import com.gyleedev.clonestagram.ui.home.HomeScreen
import com.gyleedev.clonestagram.ui.search.SearchScreen
import com.gyleedev.clonestagram.ui.setting.SettingScreen
import com.gyleedev.clonestagram.ui.upload.UploadScreen

sealed class BottomNavItem(
    val title: Int,
    val icons: BottomNavIconTypes,
    val screenRoute: String,
) {
    data object Home : BottomNavItem(
        R.string.title_home,
        BottomNavIconTypes.BottomNavDoubleImageVector(Icons.Outlined.Home, Icons.Filled.Home),
        HOME
    )

    data object Detail :
        BottomNavItem(R.string.title_detail, BottomNavIconTypes.BottomNavNoIcon, DETAIL)

    data object Search : BottomNavItem(
        R.string.title_search,
        BottomNavIconTypes.BottomNavDoubleDrawable(
            R.drawable.search_24dp_fill0_wght400_grad0_opsz24,
            R.drawable.search_24dp_fill0_wght600_grad0_opsz24
        ),
        SEARCH
    )

    data object Setting : BottomNavItem(
        R.string.title_setting,
        BottomNavIconTypes.BottomNavSingleDrawable(R.drawable.icons8_user_profile_48),
        SETTING
    )

    data object Reels : BottomNavItem(
        R.string.title_reels,
        BottomNavIconTypes.BottomNavDoubleDrawable(
            R.drawable.icons8_instagram_reels,
            R.drawable.icons8_instagram_reels__1_
        ),
        REELS
    )

    data object Upload : BottomNavItem(
        R.string.title_upload,
        BottomNavIconTypes.BottomNavSingleDrawable(
            R.drawable.icons8_add_100
        ),
        UPLOAD
    )
}

sealed interface BottomNavIconTypes {
    data class BottomNavDoubleImageVector(
        val unselectedIcons: ImageVector,
        val selectedIcons: ImageVector
    ) : BottomNavIconTypes

    data class BottomNavDoubleDrawable(
        val unselectedIcons: Int,
        val selectedIcons: Int
    ) : BottomNavIconTypes

    data class BottomNavSingleDrawable(
        val icon: Int
    ) : BottomNavIconTypes

    data class BottomNavSingleImageVector(
        val icon: ImageVector,
    ) : BottomNavIconTypes

    data object BottomNavNoIcon : BottomNavIconTypes
}


@Composable
fun CloneStagramScreen(
    navController: NavHostController = rememberNavController()
) {
    Scaffold(
        bottomBar = { BottomNavigation(navController = navController, modifier = Modifier) },
        modifier = Modifier
    ) { innerPadding ->

        NavHost(
            navController = navController,
            startDestination = BottomNavItem.Home.screenRoute,
            modifier = Modifier
                .padding(bottom = innerPadding.calculateBottomPadding())
                .statusBarsPadding()
        ) {
            composable(route = BottomNavItem.Home.screenRoute) {
                HomeScreen(modifier = Modifier.fillMaxSize())
            }

            composable(route = BottomNavItem.Detail.screenRoute) {
                DetailScreen(modifier = Modifier.fillMaxSize())
            }

            composable(route = BottomNavItem.Reels.screenRoute) {
                ReelsScreen(modifier = Modifier.fillMaxSize())
            }

            composable(route = BottomNavItem.Search.screenRoute) {
                SearchScreen(modifier = Modifier.fillMaxSize())
            }

            composable(route = BottomNavItem.Upload.screenRoute) {
                UploadScreen(modifier = Modifier.fillMaxSize())
            }

            composable(route = BottomNavItem.Setting.screenRoute) {
                SettingScreen(modifier = Modifier.fillMaxSize())
            }
        }

    }
}

@Composable
fun BottomNavigation(navController: NavHostController, modifier: Modifier) {
    val items = listOf(
        BottomNavItem.Home,
        BottomNavItem.Search,
        BottomNavItem.Upload,
        BottomNavItem.Reels,
        BottomNavItem.Setting
    )
    NavigationBar(
        modifier = modifier,
        containerColor = MaterialTheme.colorScheme.surfaceContainerLowest
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        items.forEach { item ->
            NavigationBarItem(
                icon = {
                    when (item.icons) {
                        is BottomNavIconTypes.BottomNavDoubleImageVector -> {
                            if (currentRoute == item.screenRoute) {
                                Icon(
                                    imageVector = item.icons.selectedIcons,
                                    contentDescription = null
                                )
                            } else {
                                Icon(
                                    imageVector = item.icons.unselectedIcons,
                                    contentDescription = null
                                )
                            }
                        }

                        is BottomNavIconTypes.BottomNavDoubleDrawable -> {
                            if (currentRoute == item.screenRoute) {
                                Icon(
                                    painter = painterResource(id = item.icons.selectedIcons),
                                    contentDescription = null,
                                    modifier = Modifier
                                        .size(24.dp)
                                )
                            } else {
                                Icon(
                                    painter = painterResource(id = item.icons.unselectedIcons),
                                    contentDescription = null,
                                    modifier = Modifier.size(24.dp)
                                )
                            }
                        }

                        is BottomNavIconTypes.BottomNavSingleDrawable -> {

                            Icon(
                                painter = painterResource(id = item.icons.icon),
                                contentDescription = null,
                                modifier = Modifier
                                    .size(24.dp)
                            )

                        }

                        is BottomNavIconTypes.BottomNavSingleImageVector -> {

                            Icon(
                                imageVector = item.icons.icon,
                                contentDescription = null,
                            )

                        }

                        else -> {}
                    }
                },
                selected = currentRoute == item.screenRoute,
                alwaysShowLabel = false,
                onClick = {
                    navController.navigate(item.screenRoute) {
                        navController.graph.startDestinationRoute?.let {
                            popUpTo(it) { saveState = true }
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
            )
        }
    }
}
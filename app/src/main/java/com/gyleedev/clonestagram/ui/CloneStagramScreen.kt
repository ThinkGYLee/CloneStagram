package com.gyleedev.clonestagram.ui

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.EaseIn
import androidx.compose.animation.core.EaseOut
import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarDefaults
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
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
import com.gyleedev.clonestagram.ui.home.HomeScreen
import com.gyleedev.clonestagram.ui.myprofile.MyProfileScreen
import com.gyleedev.clonestagram.ui.reels.ReelsScreen
import com.gyleedev.clonestagram.ui.search.SearchScreen
import com.gyleedev.clonestagram.ui.upload.UploadScreen

sealed class BottomNavItem(
    val icons: BottomNavIconTypes,
    val screenRoute: String
) {
    data object Home : BottomNavItem(
        BottomNavIconTypes.BottomNavDoubleImageVector(Icons.Outlined.Home, Icons.Filled.Home),
        HOME
    )

    data object Detail :
        BottomNavItem(BottomNavIconTypes.BottomNavNoIcon, DETAIL)

    data object Search : BottomNavItem(
        BottomNavIconTypes.BottomNavDoubleDrawable(
            R.drawable.search_24dp_fill0_wght400_grad0_opsz24,
            R.drawable.search_24dp_fill0_wght600_grad0_opsz24
        ),
        SEARCH
    )

    data object Setting : BottomNavItem(
        BottomNavIconTypes.BottomNavSingleDrawableImage(R.drawable.icons8_test_account_48),
        SETTING
    )

    data object Reels : BottomNavItem(
        BottomNavIconTypes.BottomNavDoubleDrawable(
            R.drawable.icons8_instagram_reels,
            R.drawable.icons8_instagram_reels__1_
        ),
        REELS
    )

    data object Upload : BottomNavItem(
        BottomNavIconTypes.BottomNavSingleDrawableIcon(
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

    data class BottomNavSingleDrawableIcon(
        val icon: Int
    ) : BottomNavIconTypes

    data class BottomNavSingleImageVector(
        val icon: ImageVector
    ) : BottomNavIconTypes

    data class BottomNavSingleDrawableImage(
        val image: Int
    ) : BottomNavIconTypes

    data object BottomNavNoIcon : BottomNavIconTypes
}

@Composable
fun CloneStagramScreen(
    navController: NavHostController = rememberNavController()
) {
    Scaffold(
        bottomBar = { BottomNavigation(navController = navController, modifier = Modifier) },
        modifier = Modifier.fillMaxSize()
    ) { paddingValue ->
        NavHost(
            navController = navController,
            startDestination = BottomNavItem.Home.screenRoute,
            enterTransition = { EnterTransition.None },
            exitTransition = { ExitTransition.None },
            modifier = Modifier
                .padding(paddingValue)
                .consumeWindowInsets(paddingValue)
        ) {
            composable(route = BottomNavItem.Home.screenRoute) {
                HomeScreen(modifier = Modifier.fillMaxSize())
            }

            composable(
                route = BottomNavItem.Detail.screenRoute
            ) {
                DetailScreen(modifier = Modifier.fillMaxSize())
            }

            composable(route = BottomNavItem.Reels.screenRoute) {
                ReelsScreen(modifier = Modifier.fillMaxSize())
            }

            composable(route = BottomNavItem.Search.screenRoute) {
                SearchScreen(modifier = Modifier.fillMaxSize())
            }

            composable(
                route = BottomNavItem.Upload.screenRoute,
                enterTransition = {
                    fadeIn(
                        animationSpec = tween(
                            300,
                            easing = FastOutLinearInEasing
                        )
                    ) + slideIntoContainer(
                        animationSpec = tween(300, easing = EaseIn),
                        towards = AnimatedContentTransitionScope.SlideDirection.End
                    )
                },
                exitTransition = {
                    fadeOut(
                        animationSpec = tween(
                            300,
                            easing = FastOutLinearInEasing
                        )
                    ) + slideOutOfContainer(
                        animationSpec = tween(300, easing = EaseOut),
                        towards = AnimatedContentTransitionScope.SlideDirection.End
                    )
                }
            ) {
                UploadScreen(
                    onClick = { navController.navigateUp() },
                    modifier = Modifier.fillMaxSize()
                )
            }

            composable(route = BottomNavItem.Setting.screenRoute) {
                MyProfileScreen(modifier = Modifier.fillMaxSize())
            }
        }
    }
}

@Composable
fun BottomNavigation(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    val items = listOf(
        BottomNavItem.Home,
        BottomNavItem.Search,
        BottomNavItem.Upload,
        BottomNavItem.Reels,
        BottomNavItem.Setting
    )
    NavigationBar(
        modifier = modifier.fillMaxWidth(),
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

                        is BottomNavIconTypes.BottomNavSingleDrawableIcon -> {
                            Icon(
                                painter = painterResource(id = item.icons.icon),
                                contentDescription = null,
                                modifier = Modifier.size(24.dp)
                            )
                        }

                        is BottomNavIconTypes.BottomNavSingleImageVector -> {
                            Icon(
                                imageVector = item.icons.icon,
                                contentDescription = null
                            )
                        }

                        is BottomNavIconTypes.BottomNavSingleDrawableImage -> {
                            Image(
                                painter = painterResource(id = item.icons.image),
                                contentDescription = null,
                                modifier = Modifier.size(24.dp)
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
                colors = NavigationBarItemDefaults.colors(
                    indicatorColor = NavigationBarDefaults.containerColor
                )
            )
        }
    }
}

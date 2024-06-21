package com.gyleedev.clonestagram.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.gyleedev.clonestagram.R
import com.gyleedev.clonestagram.ui.detail.DetailScreen
import com.gyleedev.clonestagram.ui.home.HomeScreen
import com.gyleedev.clonestagram.ui.search.SearchScreen
import com.gyleedev.clonestagram.ui.setting.SettingScreen
import com.gyleedev.clonestagram.ui.upload.UploadScreen

sealed class BottomNavItem(
    val title: Int,
    val icons: BottomNavIcons?,
    val screenRoute: String,
) {
    data object Home : BottomNavItem(
        R.string.title_home,
        BottomNavIcons(Icons.Outlined.Home, Icons.Filled.Home),
        HOME
    )

    data object Detail : BottomNavItem(R.string.title_detail, null, DETAIL)
    data object Search : BottomNavItem(
        R.string.title_search,
        BottomNavIcons(Icons.Outlined.Search, Icons.Filled.Search),
        SEARCH
    )

    data object Setting : BottomNavItem(
        R.string.title_setting,
        BottomNavIcons(Icons.Outlined.Settings, Icons.Filled.Settings),
        SETTING
    )

    data object Upload : BottomNavItem(
        R.string.title_upload,
        BottomNavIcons(Icons.Outlined.Add, Icons.Filled.Add),
        UPLOAD
    )

}

data class BottomNavIcons(
    val unselectedIcons: ImageVector,
    val selectedIcons: ImageVector
)


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
                    item.icons?.let {
                        if (currentRoute == item.screenRoute) {
                            Icon(
                                imageVector = item.icons.selectedIcons,
                                contentDescription = stringResource(id = item.title),
                                modifier = Modifier
                                    .width(26.dp)
                                    .height(26.dp),
                            )
                        } else {
                            Icon(
                                imageVector = item.icons.unselectedIcons,
                                contentDescription = stringResource(id = item.title),
                                modifier = Modifier
                                    .width(26.dp)
                                    .height(26.dp),
                            )
                        }
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
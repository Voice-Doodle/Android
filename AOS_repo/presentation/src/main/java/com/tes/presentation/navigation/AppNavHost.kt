package com.tes.presentation.navigation

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavOptions
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions
import com.tes.presentation.login.LoginScreen
import com.tes.presentation.main.MainScreen
import com.tes.presentation.mypage.MyPageScreen

@Composable
fun AppNavHost() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Route.LOGIN.destination,
        modifier = Modifier
            .fillMaxSize()
            .windowInsetsPadding(
                WindowInsets.systemBars.only(WindowInsetsSides.Vertical)
            )
    ) {
        composable(Route.LOGIN.destination) {
            LoginScreen {
                navController.navigateToDestination(
                    Route.MAIN,
                    navOptions { popUpTo(Route.LOGIN.destination) { inclusive = true } }
                )
            }
        }

        composable(Route.MAIN.destination) {
            MainScreen { navController.navigateToDestination(Route.MY_PAGE) }
        }

        composable(Route.MY_PAGE.destination) {
            MyPageScreen { navController.navigateToDestination(Route.LOGIN) }
        }
    }
}

fun NavController.navigateToDestination(route: Route, navOptions: NavOptions? = null) {
    this.navigate(route.destination, navOptions)
}

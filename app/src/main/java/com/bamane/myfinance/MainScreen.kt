package com.bamane.myfinance

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.bamane.myfinance.core.navigation.Screen
import com.bamane.myfinance.feature.dashboard.DashboardScreen
import com.bamane.myfinance.feature.friend.FriendScreen

@Composable
fun MainScreen() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screen.Dashboard.route
    ) {
        composable(Screen.Dashboard.route) {
            DashboardScreen(
                onNavigateToFriends = { navController.navigate(Screen.Friend.route) },
                onNavigateToAddBill = { navController.navigate(Screen.AddBill.route) },

            )
        }

        composable(Screen.Friend.route) {
            FriendScreen(
                onBack = { navController.popBackStack() }
            )
        }
    }
}
package com.bamane.myfinance.core.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Screen(val route: String, val title: String, val icon: ImageVector? = null) {
    object Dashboard : Screen("dashboard", "Home", Icons.Default.Home)
    object Friend : Screen("friend", "Teman", Icons.Default.Person)
    object AddBill : Screen("add_bill", "Tagihan")
}
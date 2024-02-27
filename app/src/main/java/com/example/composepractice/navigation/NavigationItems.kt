package com.example.composepractice.navigation

sealed class NavigationItems(val route: String) {
    object Home : NavigationItems("home")
    object Details : NavigationItems("details")
}

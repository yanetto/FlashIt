package com.yanetto.card_set_managment.presentation.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.yanetto.card_set_managment.presentation.HomeScreen

const val HOME_SCREEN_ROUTE = "home_screen"

fun NavController.navigateToHomeScreen() = navigate(HOME_SCREEN_ROUTE) {
    popUpTo(HOME_SCREEN_ROUTE) {
        inclusive = true
    }
}

fun NavGraphBuilder.homeScreen(
    navigateToGridScreen: (Int) -> Unit,
    navigateToCardScreen: (Int) -> Unit
) {
    composable(
        route = HOME_SCREEN_ROUTE
    ) {
        HomeScreen(
            onCardSetEditClick = { setId ->
                navigateToGridScreen(setId)
            },
            onCardSetPlayClick = { setId ->
                navigateToCardScreen(setId)
            }
        )
    }
}
package com.yanetto.card_learn.presentation.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.yanetto.card_learn.presentation.CardScreen

const val CARD_SCREEN_ROUTE = "card_screen"

fun NavController.navigateToCardScreen(setId: Int) = navigate("$CARD_SCREEN_ROUTE/$setId") {
    popUpTo("$CARD_SCREEN_ROUTE/$setId") {
        inclusive = true
    }
}

fun NavGraphBuilder.cardScreen(
    navigateBack: () -> Unit
) {
    composable(
        route = "$CARD_SCREEN_ROUTE/{setId}"
    ) { backStackEntry ->
        val setId = backStackEntry.arguments?.getString("setId")?.toIntOrNull()
        setId?.let {
            CardScreen(
                setId = it,
                onBackClick = navigateBack
            )
        }
    }
}
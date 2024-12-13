package com.yanetto.card_grid.presentation.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.yanetto.card_grid.presentation.GridScreen

const val GRID_SCREEN_ROUTE = "grid_screen"

fun NavController.navigateToGridScreen(setId: Int) = navigate("$GRID_SCREEN_ROUTE/$setId") {
    popUpTo("$GRID_SCREEN_ROUTE/$setId") {
        inclusive = true
    }
}

fun NavGraphBuilder.gridScreen(
    navigateToEditScreen: (Int) -> Unit,
    navigateToCreateScreen: (Int) -> Unit,
    navigateBack: () -> Unit
) {
    composable(
        route = "$GRID_SCREEN_ROUTE/{setId}"
    ) { backStackEntry ->
        val setId = backStackEntry.arguments?.getString("setId")?.toIntOrNull()
        setId?.let {
            GridScreen(
                setId = it,
                onEditClick = { cardId ->
                    navigateToEditScreen(cardId)
                },
                onCreateClick = { setId ->
                    navigateToCreateScreen(setId)
                },
                onBackClick = navigateBack
            )
        }
    }
}
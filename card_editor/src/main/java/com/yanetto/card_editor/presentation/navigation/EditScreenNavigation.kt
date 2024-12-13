package com.yanetto.card_editor.presentation.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.yanetto.card_editor.presentation.EditScreen

const val EDIT_SCREEN_ROUTE = "edit_screen"

fun NavController.navigateToEditScreen(cardId: Int) = navigate("$EDIT_SCREEN_ROUTE/card/$cardId") {
    popUpTo("$EDIT_SCREEN_ROUTE/card/$cardId") {
        inclusive = true
    }
}

fun NavController.navigateToCreateScreen(setId: Int) = navigate("${EDIT_SCREEN_ROUTE}/set/$setId") {
    popUpTo("${EDIT_SCREEN_ROUTE}/set/$setId") {
        inclusive = true
    }
}


fun NavGraphBuilder.editScreen(
    navigateToGridScreen: (Int) -> Unit
) {
    composable(
        route = "$EDIT_SCREEN_ROUTE/card/{cardId}"
    ) { backStackEntry ->
        val cardId = backStackEntry.arguments?.getString("cardId")?.toIntOrNull()
        cardId?.let {
            EditScreen(
                cardId = it,
                onDoneClick = { cardId ->
                    navigateToGridScreen(cardId)
                }
            )
        }
    }
}

fun NavGraphBuilder.createScreen(
    navigateToGridScreen: (Int) -> Unit
) {
    composable(
        route = "$EDIT_SCREEN_ROUTE/set/{setId}"
    ) { backStackEntry ->
        val setId = backStackEntry.arguments?.getString("setId")?.toIntOrNull()
        setId?.let {
            EditScreen(
                setId = it,
                onDoneClick = { setId ->
                    navigateToGridScreen(setId)
                }
            )
        }
    }
}
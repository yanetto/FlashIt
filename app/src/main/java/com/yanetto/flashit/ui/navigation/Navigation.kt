package com.yanetto.flashit.ui.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.yanetto.card_editor.presentation.navigation.createScreen
import com.yanetto.card_editor.presentation.navigation.editScreen
import com.yanetto.card_editor.presentation.navigation.navigateToCreateScreen
import com.yanetto.card_editor.presentation.navigation.navigateToEditScreen
import com.yanetto.card_grid.presentation.navigation.gridScreen
import com.yanetto.card_grid.presentation.navigation.navigateToGridScreen
import com.yanetto.card_learn.presentation.navigation.cardScreen
import com.yanetto.card_learn.presentation.navigation.navigateToCardScreen
import com.yanetto.card_set_managment.presentation.navigation.HOME_SCREEN_ROUTE
import com.yanetto.card_set_managment.presentation.navigation.homeScreen
import com.yanetto.card_set_managment.presentation.navigation.navigateToHomeScreen

@Composable
fun FlashItApp(
    navController: NavHostController = rememberNavController()
) {
    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = HOME_SCREEN_ROUTE,
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(innerPadding)
        ) {
            homeScreen(
                navigateToGridScreen = { setId ->
                    navController.navigateToGridScreen(setId)
                },
                navigateToCardScreen = { setId ->
                    navController.navigateToCardScreen(setId)
                }
            )

            cardScreen(
                navigateBack = {
                    navController.navigateToHomeScreen()
                }
            )

            gridScreen(
                navigateToEditScreen = { cardId ->
                    navController.navigateToEditScreen(cardId)
                },
                navigateToCreateScreen = { setId ->
                    navController.navigateToCreateScreen(setId)
                },
                navigateBack = {
                    navController.navigateToHomeScreen()
                }
            )

            editScreen(
                navigateToGridScreen = { setId ->
                    navController.navigateToGridScreen(setId)
                }
            )

            createScreen(
                navigateToGridScreen = { setId ->
                    navController.navigateToGridScreen(setId)
                }
            )
        }
    }
}




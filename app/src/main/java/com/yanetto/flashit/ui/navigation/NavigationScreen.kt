package com.yanetto.flashit.ui.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.yanetto.flashit.ui.screens.cardscreen.CardScreen
import com.yanetto.flashit.ui.screens.cardsetgridscreen.CardSetGridScreen
import com.yanetto.flashit.ui.screens.cardsetscreen.CardSetScreen
import com.yanetto.flashit.ui.screens.editscreen.EditScreen

enum class AppScreen {
    Home,
    Learn,
    Grid,
    Edit
}

@Composable
fun FlashItApp(
    navController: NavHostController = rememberNavController()
) {
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentScreen = AppScreen.valueOf(
        backStackEntry?.destination?.route?.substringBefore("/") ?: AppScreen.Home.name
    )

    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = AppScreen.Home.name,
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(innerPadding)
        ) {
            composable(route = AppScreen.Home.name) {
                CardSetScreen(
                    onCardSetEditClick = { setId ->
                        navController.navigate("${AppScreen.Grid.name}/$setId")
                    },
                    onCardSetPlayClick = { navController.navigate(AppScreen.Learn.name) }
                )
            }

            composable(route = AppScreen.Learn.name) {
                CardScreen()
            }

            composable(
                route = "${AppScreen.Grid.name}/{setId}"
            ) { backStackEntry ->
                val setId = backStackEntry.arguments?.getString("setId")?.toIntOrNull()
                setId?.let {
                    CardSetGridScreen(
                        setId = it,
                        onEditClick = { cardId ->
                            navController.navigate("${AppScreen.Edit.name}/card/$cardId")
                        },
                        onCreateClick = { setId ->
                            navController.navigate("${AppScreen.Edit.name}/set/$setId")
                        },
                        onBackClick = { navController.navigate(AppScreen.Home.name) }
                    )
                }
            }

            composable(
                route = "${AppScreen.Edit.name}/card/{cardId}"
            ) { backStackEntry ->
                val cardId = backStackEntry.arguments?.getString("cardId")?.toIntOrNull()
                cardId?.let {
                    EditScreen(
                        cardId = it,
                        onDoneClick = { setId ->
                            navController.navigate("${AppScreen.Grid.name}/$setId")
                        }
                    )
                }
            }

            composable(
                route = "${AppScreen.Edit.name}/set/{setId}"
            ) { backStackEntry ->
                val setId = backStackEntry.arguments?.getString("setId")?.toIntOrNull()
                setId?.let {
                    EditScreen(
                        setId = it,
                        onDoneClick = { setId ->
                            navController.navigate("${AppScreen.Grid.name}/$setId")
                        }
                    )
                }
            }
        }
    }
}




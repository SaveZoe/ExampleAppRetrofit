package com.example.exampleapp.exampleapp.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.exampleapp.exampleapp.base.BaseViewModel
import com.example.exampleapp.exampleapp.ui.screens.categoriesscreen.CategoriesScreen
import com.example.exampleapp.exampleapp.ui.screens.mainscreen.MainScreen
import com.example.exampleapp.exampleapp.ui.screens.splashscreen.SplashScreen

@Composable
fun NavigationGraph() {
    val navController = rememberNavController()
    val viewModel = hiltViewModel<BaseViewModel>()
    NavHost(
        navController = navController,
        startDestination = NavigationTree.Root.Splash.name
    ) {
        composable(NavigationTree.Root.Splash.name) {
            SplashScreen(navController = navController, viewModel = viewModel)
        }
        composable(NavigationTree.Root.Categories.name) {
            CategoriesScreen(viewModel = viewModel, navController = navController)
        }
        composable(NavigationTree.Root.Main.name + "/{item.strCategory}") {
            MainScreen(
                viewModel = viewModel
            )
        }
    }
}
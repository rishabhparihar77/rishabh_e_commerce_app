package com.rishabh.e_commerceapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.rishabh.e_commerceapp.screens.intro_screen.IntroScreen
import com.rishabh.e_commerceapp.screens.product_cart.CartScreen
import com.rishabh.e_commerceapp.screens.product_detail.ProductDetailScreen
import com.rishabh.e_commerceapp.screens.product_list.ProductListScreen

@Composable
fun AppNavGraph() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screen.Intro.route
    ) {

        composable(Screen.Intro.route) {
            IntroScreen {
                navController.navigate(Screen.ProductList.route) {
                    popUpTo(Screen.Intro.route) { inclusive = true }
                }
            }
        }

        composable(Screen.ProductList.route) {
            ProductListScreen(
                onProductClick = {
                    navController.navigate(Screen.ProductDetail.create(it))
                },
                onCartClick = {
                    navController.navigate(Screen.Cart.route)
                }
            )
        }

        composable(
            Screen.ProductDetail.route,
            arguments = listOf(navArgument("id") { type = NavType.IntType })
        ) {
            ProductDetailScreen(
                onBack = { navController.popBackStack() },
                onCartClick = { navController.navigate(Screen.Cart.route) }
            )
        }

        composable(Screen.Cart.route) {
            CartScreen(onBack = { navController.popBackStack() })
        }
    }
}

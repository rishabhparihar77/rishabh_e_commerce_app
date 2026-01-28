package com.rishabh.e_commerceapp.navigation

sealed class Screen(val route: String) {
    object Intro : Screen("intro")
    object ProductList : Screen("products")
    object ProductDetail : Screen("product_detail/{id}") {
        fun create(id: Int) = "product_detail/$id"
    }
    object Cart : Screen("cart")
}

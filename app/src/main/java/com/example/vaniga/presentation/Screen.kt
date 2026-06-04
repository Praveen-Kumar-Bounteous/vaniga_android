package com.example.vaniga.presentation

sealed class Screen(val route: String) {
    object ProductListScreen : Screen("product_list")
    object ProductDetailScreen : Screen("product_detail")
}
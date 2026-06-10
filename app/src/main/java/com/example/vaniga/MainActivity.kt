package com.example.vaniga

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import dagger.hilt.android.AndroidEntryPoint
import com.example.vaniga.presentation.Screen
import com.example.vaniga.presentation.product_detail.ProductDetailScreen
import com.example.vaniga.presentation.product_list.ProductListScreen
import com.example.vaniga.ui.theme.VanigaTheme
import com.example.vaniga.presentation.home.HomeScreen

@OptIn(ExperimentalSharedTransitionApi::class)
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            VanigaTheme {
                Surface(color = MaterialTheme.colorScheme.background) {
                    val navController = rememberNavController()

                    SharedTransitionLayout {
                        NavHost(
                            navController = navController,
                            startDestination = Screen.HomeScreen.route
                        ) {
                            composable(Screen.HomeScreen.route) {
                                this@SharedTransitionLayout.HomeScreen(
                                    navController = navController,
                                    animatedVisibilityScope = this@composable
                                )
                            }

                            composable(route = Screen.ProductListScreen.route) {
                                this@SharedTransitionLayout.ProductListScreen(
                                    navController = navController,
                                    animatedVisibilityScope = this@composable
                                )
                            }

                            composable(
                                route = Screen.ProductDetailScreen.route + "/{productId}",
                                arguments = listOf(
                                    navArgument("productId") {
                                        type = NavType.IntType
                                    }
                                )
                            ) {
                                this@SharedTransitionLayout.ProductDetailScreen(
                                    navController = navController,
                                    animatedVisibilityScope = this@composable
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}
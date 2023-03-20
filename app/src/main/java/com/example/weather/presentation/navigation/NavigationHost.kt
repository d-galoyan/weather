package com.example.weather.presentation.navigation

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.example.weather.presentation.screens.addCity.AddCityRoute
import com.example.weather.presentation.screens.addCity.AddCityScreen
import com.example.weather.presentation.screens.addCity.SettingsLayout
import com.example.weather.presentation.screens.details.DetailsDestination
import com.example.weather.presentation.screens.details.DetailsScreen
import com.example.weather.presentation.screens.details.DetailsScreenLayout
import com.example.weather.presentation.screens.home.HomeDestination
import com.example.weather.presentation.screens.home.HomeScreen
import com.example.weather.presentation.screens.home.HomeScreenLayout
import com.example.weather.presentation.screens.settings.SettingScreen
import com.example.weather.presentation.screens.settings.SettingsDestination
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.rememberAnimatedNavController

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun WeatherNavHost(
    navController: NavHostController = rememberAnimatedNavController(),
) {
    AnimatedNavHost(
        navController = navController,
        startDestination = HomeDestination.route,
    ) {
        composable(route = HomeDestination.route,
            enterTransition = {
                when (initialState.destination.route) {
                    AddCityRoute.route -> slideIntoContainer(
                        AnimatedContentScope.SlideDirection.Down,
                        animationSpec = tween(500)
                    ) // slowly fade it out
                    else -> slideIntoContainer(
                        AnimatedContentScope.SlideDirection.Left,
                        animationSpec = tween(500)
                    )
                }
            },
            exitTransition = {
                when (targetState.destination.route) {
                    AddCityRoute.route -> slideOutOfContainer(
                        AnimatedContentScope.SlideDirection.Up,
                        animationSpec = tween(500)
                    ) // slowly fade it out
                    else -> slideOutOfContainer(
                        AnimatedContentScope.SlideDirection.Right,
                        animationSpec = tween(500)
                    )
                }
            }
        ) {
            HomeScreenLayout(navController = navController) {
                HomeScreen(
                    navigateToDetails = { navController.navigate("${DetailsDestination.route}/${it}") },
                    onNewCityClick = { navController.navigate(AddCityRoute.route) }
                )
            }
        }
        composable(
            route = DetailsDestination.routeWithArgs,
            arguments = listOf(navArgument(DetailsDestination.detailsIdArg) {
                type = NavType.IntType
            }),
            enterTransition = {
                slideIntoContainer(
                    AnimatedContentScope.SlideDirection.Right,
                    animationSpec = tween(500)
                )
            },
            exitTransition = {
                slideOutOfContainer(
                    AnimatedContentScope.SlideDirection.Left,
                    animationSpec = tween(700)
                )
            }
        ) {
            DetailsScreenLayout(navController = navController) {
                DetailsScreen()
            }
        }
        composable(route = AddCityRoute.route,
            enterTransition = {
                slideIntoContainer(
                    AnimatedContentScope.SlideDirection.Down,
                    animationSpec = tween(500)
                )
            },
            exitTransition = {
                slideOutOfContainer(
                    AnimatedContentScope.SlideDirection.Up,
                    animationSpec = tween(500)
                )
            }
        ) {
            AddCityScreen(onBack = { navController.popBackStack() })
        }
        composable(route = SettingsDestination.route,
            enterTransition = {
                slideIntoContainer(
                    AnimatedContentScope.SlideDirection.Right,
                    animationSpec = tween(500)
                )
            },
            exitTransition = {
                slideOutOfContainer(
                    AnimatedContentScope.SlideDirection.Left,
                    animationSpec = tween(500)
                )
            }
        ) {
            SettingsLayout(navController = navController) {
                SettingScreen()
            }
        }
    }
}

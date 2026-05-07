package com.jlls.soundbrain.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.jlls.soundbrain.ui.screens.generador.GeneradorScreen
import com.jlls.soundbrain.ui.screens.inicio.InicioScreen
import com.jlls.soundbrain.ui.screens.perfil.PerfilScreen

/**
 * Main navigation graph for SoundBrain Music AI.
 * Contains the 3 main tab destinations.
 */
@Composable
fun AppNavGraph(
    navController: NavHostController,
    startDestination: String = Screen.Inicio.route
) {
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable(route = Screen.Inicio.route) {
            InicioScreen()
        }
        composable(route = Screen.Generador.route) {
            GeneradorScreen()
        }
        composable(route = Screen.Perfil.route) {
            PerfilScreen()
        }
    }
}

package com.RCS.my_app.ui.components

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.RCS.my_app.ui.screen.login.LoginScreen
import com.RCS.my_app.ui.screen.inicial.LoginCreateAccountScreen
import com.RCS.my_app.ui.screen.reguister.RegisterScreen
import com.RCS.my_app.ui.screen.mainmenu.WelcomeScreen
// app/ui/navigation/NavGraph.kt
@Composable
fun AppNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = "login_creator"
    ) {
        composable("login_creator") {
            LoginCreateAccountScreen(
                onLoginClick = { navController.navigate("login") },
                onCreateAccountClick = { navController.navigate("register") }
            )
        }

        // Pantalla de Login
        composable("login") {
            LoginScreen(
                onNavigateBack = { navController.popBackStack() },
                onLoginSuccess = {
                    navController.navigate("welcome") {
                        popUpTo("login_creator") { inclusive = true }
                    }
                },
                onNavigateToRegister = { navController.navigate("register") }
            )
        }

        // Pantalla de Registro
        composable("register") {
            RegisterScreen(
                onNavigateBack = { navController.popBackStack() },
                onRegisterSuccess = {
                    navController.navigate("welcome") {
                        popUpTo("login_creator") { inclusive = true }
                    }
                },
                onNavigateToLogin = { navController.navigate("login") }
            )
        }

        // Pantalla de Bienvenida (post-login)
        composable("welcome") {
            WelcomeScreen(
                onLogout = {
                    navController.navigate("login_creator") {
                        popUpTo("welcome") { inclusive = true }
                    }
                }
            )
        }
    }
}
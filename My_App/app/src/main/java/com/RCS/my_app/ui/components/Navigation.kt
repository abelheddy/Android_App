package com.RCS.my_app.ui.components

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.RCS.my_app.ui.screen.forgotpassword.ForgotPasswordScreen
import com.RCS.my_app.ui.screen.inicial.LoginCreateAccountScreen

import com.RCS.my_app.ui.screen.mainmenu.WelcomeScreen
import com.RCS.my_app.ui.screens.login.LoginScreen
import com.RCS.my_app.ui.screens.register.RegisterScreen

// app/ui/navigation/NavGraph.kt

@Composable
fun AppNavGraph(
    navController: NavHostController,
    startDestination: String = "login_creator" // Pantalla inicial
) {
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        // Pantalla inicial (Login/Crear cuenta)
        composable("login_creator") {
            LoginCreateAccountScreen(
                onLoginClick = { navController.navigate("login") },
                onCreateAccountClick = { navController.navigate("register") },
                onBack = { navController.popBackStack() }
            )
        }

        // Login
        composable("login") {
            LoginScreen(
                onNavigateBack = { navController.popBackStack() },
                onLoginSuccess = {
                    navController.navigate("welcome") {
                        popUpTo("login_creator") { inclusive = true }
                    }
                },
                onNavigateToRegister = { navController.navigate("register") },
                onNavigateToForgotPassword = { navController.navigate("forgot_password") }
            )
        }

        // Registro
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

        // Recuperación de contraseña
        composable("forgot_password") {
            ForgotPasswordScreen(
                onBack = { navController.popBackStack() },
                onSendInstructions = { email ->
                    // Lógica para enviar email (ej: Firebase Auth)
                    navController.popBackStack()
                }
            )
        }

        // Pantalla post-login (Home/Welcome)
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

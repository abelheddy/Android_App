// app/ui/components/NavGraph.kt
package com.RCS.my_app.ui.components

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.RCS.my_app.ui.screen.forgotpassword.ForgotPasswordScreen
import com.RCS.my_app.ui.screen.inicial.LoginCreateAccountScreen
import com.RCS.my_app.ui.screen.mainmenu.WelcomeScreen
import com.RCS.my_app.ui.screen.verifycode.VerifyCodeScreen
import com.RCS.my_app.ui.screens.login.LoginScreen
import com.RCS.my_app.ui.screen.reguister.RegisterScreen
import com.RCS.my_app.ui.screen.forgotpassword.ResetPasswordScreen

@Composable
fun AppNavGraph(
    navController: NavHostController,
    startDestination: String = "login_creator"
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

        composable("register") {
            RegisterScreen(
                onNavigateBack = { navController.popBackStack() },
                onRegisterSuccess = { email ->
                    // Ahora recibe el email y lo pasa a la pantalla de verificación
                    navController.navigate("verify_code/register/$email")
                },
                onNavigateToLogin = { navController.navigate("login") }
            )
        }
        // Recuperación de contraseña
        composable("forgot_password") {
            ForgotPasswordScreen(
                onBack = { navController.popBackStack() },
                onSendInstructions = { email ->
                    navController.navigate("verify_code/forgot/$email")
                }
            )
        }

        // Pantalla de verificación de código (reutilizable)
        composable(
            route = "verify_code/{context}/{email}",
            arguments = listOf(
                navArgument("context") { type = NavType.StringType },
                navArgument("email") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val context = backStackEntry.arguments?.getString("context") ?: ""
            val email = backStackEntry.arguments?.getString("email") ?: ""

            VerifyCodeScreen(
                onVerify = { code ->
                    when (context) {
                        "register" -> {
                            // Verificación exitosa en registro
                            navController.navigate("welcome") {
                                popUpTo("login_creator") { inclusive = true }
                            }
                        }
                        "forgot" -> {
                            // Verificación exitosa en recuperación
                            navController.navigate("reset_password/$email/$code")
                        }
                    }
                },
                onResendCode = {
                    // Lógica para reenviar código
                    // Puedes llamar a tu servicio de autenticación aquí
                },
                onBack = { navController.popBackStack() }
            )
        }

        // Pantalla de cambio de contraseña
        composable(
            route = "reset_password/{email}/{code}",
            arguments = listOf(
                navArgument("email") { type = NavType.StringType },
                navArgument("code") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val email = backStackEntry.arguments?.getString("email") ?: ""
            val code = backStackEntry.arguments?.getString("code") ?: ""

            ResetPasswordScreen(
                email = email,
                verificationCode = code,
                onPasswordReset = {
                    // Aquí iría la lógica para cambiar la contraseña en tu backend
                    // Después de cambiar la contraseña, navegar al login
                    navController.navigate("login") {
                        popUpTo("login_creator") { inclusive = true }
                    }
                },
                onBack = { navController.popBackStack() }
            )
        }

        // Pantalla principal post-login
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
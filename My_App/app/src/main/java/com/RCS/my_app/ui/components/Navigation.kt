// app/ui/components/NavGraph.kt
package com.RCS.my_app.ui.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.RCS.my_app.ui.screen.forgotpassword.ForgotPasswordScreen
import com.RCS.my_app.ui.screen.inicial.LoginCreateAccountScreen
import com.RCS.my_app.ui.screen.home.HomeScreen
import com.RCS.my_app.ui.screen.verifycode.VerifyCodeScreen
import com.RCS.my_app.ui.screens.login.LoginScreen
import com.RCS.my_app.ui.screen.reguister.RegisterScreen
import com.RCS.my_app.ui.screen.forgotpassword.ResetPasswordScreen
import com.RCS.my_app.ui.screen.notifications.NotificationsScreen
import com.RCS.my_app.ui.screen.profile.ProfileScreen
import com.RCS.my_app.ui.screen.search.SearchScreen
import com.RCS.my_app.ui.screen.upload.UploadRecipeScreen
import com.RCS.my_app.ui.screen.view.ViewRecipesScreen

@Composable
fun AppNavGraph(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    startDestination: String = "login_creator"
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
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
                    // Navegar a pantalla de verificación o directamente a home
                    navController.navigate("login") {
                        popUpTo("auth") { inclusive = true }
                    }
                },
                onNavigateToLogin = {
                    navController.navigate("login") {
                        popUpTo("register") { inclusive = true }
                    }
                }
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
        // Pantalla de search
        composable("search") {
            SearchScreen(
                currentRoute = "search",
                onNavigate = { route -> navController.navigate(route) }
            )
        }
        // Pantalla de notification
        composable("notifications") {
            NotificationsScreen(
                currentRoute = "notifications",
                onNavigate = { route -> navController.navigate(route) }
            )
        }
        composable("profile") {
            ProfileScreen(
                currentRoute = "profile",
                onNavigate = { route -> navController.navigate(route) }
            )
        }

        composable("upload_recipe") {
            UploadRecipeScreen(onBack = { navController.popBackStack() })
        }

        composable("view_recipes") {
            ViewRecipesScreen(onBack = { navController.popBackStack() })
        }
        // ui/components/NavGraph.kt (fragmento relevante)
        composable("home") {
            HomeScreen(
                currentRoute = "home",
                onNavigate = { route -> navController.navigate(route) },
                onAddRecipeClick = { navController.navigate("upload_recipe") },
                onViewRecipesClick = { navController.navigate("view_recipes") }
            )
        }
    }
}
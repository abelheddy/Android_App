// app/ui/screens/login/LoginScreen.kt
package com.RCS.my_app.ui.screens.login

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.RCS.my_app.ui.components.buttons.GlassButton
import com.RCS.my_app.ui.components.text.TextLink
import com.RCS.my_app.ui.layouts.auth.CommonAuthLayout
import com.RCS.my_app.ui.layouts.auth.AuthTextField

@Composable
fun LoginScreen(
    onNavigateBack: () -> Unit,
    onLoginSuccess: () -> Unit,
    onNavigateToRegister: () -> Unit,
    onNavigateToForgotPassword: () -> Unit
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    CommonAuthLayout(
        title = "Iniciar Sesión",
        content = {
            AuthTextField(
                value = email,
                onValueChange = { email = it },
                label = "Correo electrónico",
                icon = Icons.Default.Email,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(16.dp))
            AuthTextField(
                value = password,
                onValueChange = { password = it },
                label = "Contraseña",
                icon = Icons.Default.Lock,
                isPassword = true,
                modifier = Modifier.fillMaxWidth()
            )
            TextLink(
                text = "¿Olvidaste tu contraseña?",
                onClick = onNavigateToForgotPassword,
                modifier = Modifier.align(Alignment.End)
            )
        },
        bottomContent = {
            GlassButton(
                text = "Ingresar",
                onClick = {  },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))
            TextLink(
                text = "¿No tienes cuenta? Regístrate",
                onClick = onNavigateToRegister,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
        },
    )
}
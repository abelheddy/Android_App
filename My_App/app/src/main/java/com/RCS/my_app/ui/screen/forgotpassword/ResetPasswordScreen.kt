package com.RCS.my_app.ui.screen.forgotpassword

import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.RCS.my_app.ui.components.buttons.GlassButton
import com.RCS.my_app.ui.layouts.auth.AuthTextField
import com.RCS.my_app.ui.layouts.auth.CommonAuthLayout
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign

@Composable
fun ResetPasswordScreen(
    email: String,
    verificationCode: String,
    onPasswordReset: () -> Unit,
    onBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    var newPassword by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    var passwordsMatch by remember { mutableStateOf(true) }

    CommonAuthLayout(
        title = "Cambiar Contraseña",
        subtitle = "Para la cuenta: $email",
        modifier = modifier,
        content = {
            Spacer(modifier = Modifier.height(32.dp))

            // Campo para nueva contraseña
            AuthTextField(
                value = newPassword,
                onValueChange = { newPassword = it },
                label = "Nueva contraseña",
                icon = Icons.Default.Lock,
                isPassword = true,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Campo para confirmar contraseña
            AuthTextField(
                value = confirmPassword,
                onValueChange = { confirmPassword = it },
                label = "Confirmar contraseña",
                icon = Icons.Default.Lock,
                isPassword = true,
                modifier = Modifier.fillMaxWidth()
            )

            if (!passwordsMatch) {
                Text(
                    text = "Las contraseñas no coinciden",
                    color = Color.Red,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp),
                    textAlign = TextAlign.Center
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Botón para cambiar contraseña
            GlassButton(
                text = "Cambiar Contraseña",
                onClick = {
                    passwordsMatch = newPassword == confirmPassword
                    if (passwordsMatch && newPassword.isNotEmpty()) {
                        onPasswordReset()
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
            )
        },
        bottomContent = {
            // Opcional: Botón para volver atrás
            GlassButton(
                text = "Volver",
                onClick = onBack,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp),
                tintColor = Color.Gray.copy(alpha = 0.5f)
            )
        }
    )
}
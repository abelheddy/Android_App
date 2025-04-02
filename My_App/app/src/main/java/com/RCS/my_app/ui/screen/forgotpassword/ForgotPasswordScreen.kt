package com.RCS.my_app.ui.screen.forgotpassword

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import android.widget.Toast
import com.RCS.my_app.ui.components.buttons.GlassButton
import com.RCS.my_app.ui.components.text.TextLink
import com.RCS.my_app.ui.layouts.auth.CommonAuthLayout
import com.RCS.my_app.ui.layouts.auth.AuthTextField

@Composable
fun ForgotPasswordScreen(
    onBack: () -> Unit,  // Navegar hacia atrás
    onSendInstructions: (String) -> Unit = {}  // Lógica para enviar email
) {
    val context = LocalContext.current
    var email by remember { mutableStateOf("") }

    CommonAuthLayout(
        title = "Recuperar contraseña",
        subtitle = "Ingresa tu email para recibir instrucciones",
        content = {
            AuthTextField(
                value = email,
                onValueChange = { email = it },
                label = "Correo electrónico",
                icon = Icons.Default.Email,
                modifier = Modifier.fillMaxWidth()
            )
        },
        bottomContent = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .navigationBarsPadding()
                    .padding(bottom = 32.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                GlassButton(
                    text = "Enviar instrucciones",
                    onClick = {
                        if (email.isBlank()) {
                            Toast.makeText(
                                context,
                                "Ingresa un email válido",
                                Toast.LENGTH_SHORT
                            ).show()
                        } else {
                            onSendInstructions(email)
                            Toast.makeText(
                                context,
                                "Instrucciones enviadas a $email",
                                Toast.LENGTH_SHORT
                            ).show()
                            onBack()
                        }
                    },
                    modifier = Modifier.fillMaxWidth()
                )
                TextLink(
                    text = "Volver al login",
                    onClick = onBack
                )
            }
        },
    )
}
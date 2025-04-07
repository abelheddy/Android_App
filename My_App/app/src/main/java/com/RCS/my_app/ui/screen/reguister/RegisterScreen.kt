// app/ui/screens/register/RegisterScreen.kt
package com.RCS.my_app.ui.screen.reguister

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.RCS.my_app.ui.components.buttons.GlassButton
import com.RCS.my_app.ui.components.text.TextLink
import com.RCS.my_app.ui.layouts.auth.CommonAuthLayout
import com.RCS.my_app.ui.layouts.auth.AuthTextField
import com.RCS.my_app.ui.screen.view.RegisterViewModel

@Composable
fun RegisterScreen(
    onNavigateBack: () -> Unit,
    onRegisterSuccess: (String) -> Unit, // Llamar con el email o el token, dependiendo de la lógica
    onNavigateToLogin: () -> Unit,
    registerViewModel: RegisterViewModel = viewModel() // Usamos el ViewModel para gestionar el registro
) {
    // Variables locales para los campos
    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    var showError by remember { mutableStateOf(false) }

    // Observamos el estado de registro desde el ViewModel
    val registerState by registerViewModel.registerState.collectAsState()

    // Mostrar estado de carga
    when (registerState) {
        is RegisterViewModel.RegisterState.Loading -> {
            // Mostrar un loading
            // Puedes usar un ProgressIndicator si lo deseas
        }
        is RegisterViewModel.RegisterState.Success -> {
            val token = (registerState as RegisterViewModel.RegisterState.Success).token
            onRegisterSuccess(token)  // Cuando el registro sea exitoso, navegar o procesar
        }
        is RegisterViewModel.RegisterState.Error -> {
            val errorMessage = (registerState as RegisterViewModel.RegisterState.Error).message
            showError = true
            // Mostrar el mensaje de error
            Toast.makeText(LocalContext.current, errorMessage, Toast.LENGTH_LONG).show()
        }
        else -> {}
    }

    CommonAuthLayout(
        title = "Crear Cuenta",
        content = {
            AuthTextField(
                value = name,
                onValueChange = { name = it },
                label = "Nombre completo",
                icon = Icons.Default.Person,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(16.dp))
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
            Spacer(modifier = Modifier.height(16.dp))
            AuthTextField(
                value = confirmPassword,
                onValueChange = { confirmPassword = it },
                label = "Confirmar contraseña",
                icon = Icons.Default.Lock,
                isPassword = true,
                modifier = Modifier.fillMaxWidth()
            )

            if (showError) {
                Text(
                    text = "Las contraseñas no coinciden",
                    color = Color.Red,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp),
                    textAlign = TextAlign.Center
                )
            }
        },
        bottomContent = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .navigationBarsPadding()
                    .padding(bottom = 32.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                GlassButton(
                    text = "Registrarse",
                    onClick = {
                        // Validar que las contraseñas coinciden antes de hacer la llamada
                        if (password == confirmPassword && password.isNotEmpty()) {
                            registerViewModel.register(name, email, password, confirmPassword)
                        } else {
                            showError = true
                        }
                    },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(8.dp))
                TextLink(
                    text = "¿Ya tienes cuenta? Inicia Sesión",
                    onClick = onNavigateToLogin,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
            }
        }
    )
}
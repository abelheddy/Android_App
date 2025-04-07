// app/ui/screens/register/RegisterScreen.kt
package com.RCS.my_app.ui.screen.reguister

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
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
import com.RCS.my_app.ui.viewmodels.RegisterViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterScreen(
    onNavigateBack: () -> Unit,
    onRegisterSuccess: (String) -> Unit,
    onNavigateToLogin: () -> Unit
) {
    val context = LocalContext.current
    val viewModel: RegisterViewModel = viewModel()
    val registerState by viewModel.registerState.collectAsState()
    val snackbarHostState = remember { SnackbarHostState() }

    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    var showError by remember { mutableStateOf(false) }

    // Manejo de estados del registro
    LaunchedEffect(registerState) {
        when (val state = registerState) {
            is RegisterViewModel.RegisterState.Success -> {
                onRegisterSuccess(state.email)
            }
            is RegisterViewModel.RegisterState.Error -> {
                snackbarHostState.showSnackbar(state.message)
            }
            else -> {}
        }
    }

    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) }
    ) { innerPadding ->
        CommonAuthLayout(
            modifier = Modifier.padding(innerPadding),
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
                            if (password == confirmPassword && password.isNotEmpty()) {
                                showError = false
                                viewModel.register(name, email, password)
                            } else {
                                showError = true
                            }
                        },
                        modifier = Modifier.fillMaxWidth(),
                        enabled = registerState !is RegisterViewModel.RegisterState.Loading
                    )

                    if (registerState is RegisterViewModel.RegisterState.Loading) {
                        CircularProgressIndicator(
                            modifier = Modifier.align(Alignment.CenterHorizontally)
                        )
                    }

                    TextLink(
                        text = "¿Ya tienes cuenta? Inicia Sesión",
                        onClick = onNavigateToLogin,
                        modifier = Modifier.align(Alignment.CenterHorizontally)
                    )
                }
            }
        )
    }
}
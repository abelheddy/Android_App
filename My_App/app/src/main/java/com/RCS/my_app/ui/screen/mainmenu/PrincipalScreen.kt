package com.RCS.my_app.ui.screen.mainmenu

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.*
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.RCS.my_app.ui.components.buttons.RoundedButton


@Composable
fun WelcomeScreen(onNavigateToLogin: () -> Unit,  // Cambiado de onLoginClick
                  onNavigateToRegister: () -> Unit,  // Cambiado de onCreateAccountClick
                  modifier: Modifier = Modifier
) {
    Surface(modifier = modifier.fillMaxSize()) {
        Column(
            modifier = Modifier.padding(32.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Help your path to health goals with happiness",
                style = MaterialTheme.typography.headlineSmall,
                modifier = Modifier.padding(bottom = 48.dp)
            )

            // Botón de Login
            RoundedButton(
                text = "Login",
                onClick = onNavigateToLogin,  // Usando el nuevo nombre
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(Modifier.height(16.dp))

            // Botón de Registro
            RoundedButton(
                text = "Create New Account",
                onClick = onNavigateToRegister,  // Usando el nuevo nombre
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}
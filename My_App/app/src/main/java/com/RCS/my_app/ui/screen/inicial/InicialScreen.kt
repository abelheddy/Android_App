package com.RCS.my_app.ui.screen.inicial

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.RCS.my_app.ui.components.CommonAuthLayout

// app/ui/screen/inicial/LoginCreateAccountScreen.kt
@Composable
fun LoginCreateAccountScreen(
    onLoginClick: () -> Unit,
    onCreateAccountClick: () -> Unit
) {
    CommonAuthLayout(
        title = "Bienvenido a MyApp",
        subtitle = "Comienza tu experiencia saludable",
        bottomContent = {
            GlassButton(
                text = "Iniciar Sesión",
                onClick = onLoginClick,
                modifier = Modifier.fillMaxWidth()
            )
            GlassButton(
                text = "Crear Cuenta",
                onClick = onCreateAccountClick,
                modifier = Modifier.fillMaxWidth(),
                tintColor = Color.White.copy(alpha = 0.9f),
                textColor = MaterialTheme.colorScheme.primary
            )
        }
    ) {
        // Espacio para contenido adicional si es necesario
    }
}
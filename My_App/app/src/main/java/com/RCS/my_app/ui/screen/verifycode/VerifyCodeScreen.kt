package com.RCS.my_app.ui.screen.verifycode

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.VerifiedUser
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.RCS.my_app.ui.components.buttons.GlassButton
import com.RCS.my_app.ui.layouts.auth.AuthTextField
import com.RCS.my_app.ui.layouts.auth.CommonAuthLayout

@Composable
fun VerifyCodeScreen(
    onVerify: (String) -> Unit,
    onResendCode: () -> Unit,
    onBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    var verificationCode by remember { mutableStateOf("") }

    CommonAuthLayout(
        title = "Verificación de Código",
        subtitle = "Ingresa el código de 6 dígitos que enviamos a tu correo",
        modifier = modifier,
        content = {
            Spacer(modifier = Modifier.height(32.dp))

            AuthTextField(
                value = verificationCode,
                onValueChange = {
                    if (it.length <= 6) verificationCode = it
                },
                label = "Código de verificación",
                icon = Icons.Default.VerifiedUser,
                modifier = Modifier.fillMaxWidth(),
            )

            Spacer(modifier = Modifier.height(16.dp))

            GlassButton(
                text = "Verificar Código",
                onClick = {  },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                tintColor = MaterialTheme.colorScheme.primary
            )
        },
        bottomContent = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .navigationBarsPadding()
                    .padding(bottom = 32.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ){
                Text(
                    text = "¿No recibiste el código? ",
                    style = MaterialTheme.typography.bodyMedium.copy(
                        color = Color.White.copy(alpha = 0.8f)
                    )
                )
                Text(
                    text = "Reenviar",
                    style = MaterialTheme.typography.bodyMedium.copy(
                        color = MaterialTheme.colorScheme.primary
                    ),
                    modifier = Modifier.clickable { onResendCode() }
                )
            }
        }
    )
}
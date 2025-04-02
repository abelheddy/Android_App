package com.RCS.my_app.ui.screen.inicial

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding

import androidx.compose.material3.ExperimentalMaterial3Api

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.RCS.my_app.R
import com.RCS.my_app.ui.components.buttons.GlassButton
import com.RCS.my_app.ui.layouts.auth.CommonAuthLayout

// app/ui/screen/inicial/LoginCreateAccountScreen.kt
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginCreateAccountScreen(
    onLoginClick: () -> Unit,
    onCreateAccountClick: () -> Unit,
    onBack: () -> Unit
) {
    Scaffold(
        content = { paddingValues ->
            CommonAuthLayout(
                modifier = Modifier.padding(paddingValues),
                title = stringResource(R.string.welcome_title),
                subtitle = stringResource(R.string.welcome_subtitle),
                content = {
                    Spacer(modifier = Modifier.height(32.dp))
                    /* Opcional: Logo de la app
                    Image(
                        painter = painterResource(R.drawable.ic_logo),
                        contentDescription = null,
                        modifier = Modifier.size(120.dp)
                    )
                    */
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
                            text = stringResource(R.string.login_button),
                            onClick = onLoginClick,
                            modifier = Modifier.fillMaxWidth()
                        )
                        GlassButton(
                            text = stringResource(R.string.create_account_button),
                            onClick = onCreateAccountClick,
                            modifier = Modifier.fillMaxWidth(),
                            tintColor = Color.White.copy(alpha = 0.9f),
                            textColor = MaterialTheme.colorScheme.primary
                        )
                    }
                }
            )
        }
    )
}
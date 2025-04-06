package com.RCS.my_app.ui.screen.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.RCS.my_app.ui.components.navigation.AppBottomNavBar

@Composable
fun ProfileScreen(
    onNavigate: (String) -> Unit,
    currentRoute: String,
    modifier: Modifier = Modifier
) {
    Scaffold(
        bottomBar = { AppBottomNavBar(currentRoute, onNavigate) }
    ) { paddingValues ->
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .size(120.dp)
                    .clip(CircleShape)
                    .background(MaterialTheme.colorScheme.surfaceVariant)
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Alena Sabyan",
                style = MaterialTheme.typography.headlineMedium
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Miembro desde 2023",
                style = MaterialTheme.typography.bodyMedium
            )

            Spacer(modifier = Modifier.height(24.dp))

            Divider(modifier = Modifier.fillMaxWidth())

            ListItem(
                headlineContent = { Text("Mis recetas") },
                leadingContent = { Icon(Icons.Default.Book, null) },
                trailingContent = { Icon(Icons.Default.ChevronRight, null) }
            )

            ListItem(
                headlineContent = { Text("Favoritos") },
                leadingContent = { Icon(Icons.Default.Favorite, null) },
                trailingContent = { Icon(Icons.Default.ChevronRight, null) }
            )

            ListItem(
                headlineContent = { Text("Configuración") },
                leadingContent = { Icon(Icons.Default.Settings, null) },
                trailingContent = { Icon(Icons.Default.ChevronRight, null) }
            )
        }
    }
}
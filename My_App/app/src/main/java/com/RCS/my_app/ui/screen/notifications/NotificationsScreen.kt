package com.RCS.my_app.ui.screen.notifications

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.RCS.my_app.ui.components.navigation.AppBottomNavBar

@Composable
fun NotificationsScreen(
    onNavigate: (String) -> Unit,
    currentRoute: String,
    modifier: Modifier = Modifier
) {
    Scaffold(
        bottomBar = { AppBottomNavBar(currentRoute, onNavigate) }
    ) { paddingValues ->
        LazyColumn(
            modifier = modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(10) { index ->
                Card(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    ListItem(
                        headlineContent = { Text("Notificación $index") },
                        supportingContent = { Text("Descripción de la notificación") },
                        leadingContent = {
                            Icon(
                                imageVector = when (index % 3) {
                                    0 -> Icons.Default.Favorite
                                    1 -> Icons.Default.Comment
                                    else -> Icons.Default.Share
                                },
                                contentDescription = null
                            )
                        }
                    )
                }
            }
        }
    }
}
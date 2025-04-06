package com.RCS.my_app.ui.screen.search

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.RCS.my_app.ui.components.navigation.AppBottomNavBar

@Composable
fun SearchScreen(
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
                .padding(16.dp)
        ) {
            OutlinedTextField(
                value = "",
                onValueChange = {},
                leadingIcon = { Icon(Icons.Default.Search, "Buscar") },
                label = { Text("Buscar recetas") },
                modifier = Modifier.fillMaxWidth()
            )

            // Aquí irían los resultados de búsqueda
        }
    }
}
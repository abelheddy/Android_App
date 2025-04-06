package com.RCS.my_app.ui.screen.view

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.RCS.my_app.ui.components.cards.PopularRecipeCard

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ViewRecipesScreen(
    onBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    val recipes = listOf(
        Recipe("Pasta Carbonara", "450", "2"),
        Recipe("Ensalada César", "320", "1"),
        Recipe("Sopa de Tomate", "280", "4")
    )

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Mis recetas") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.Default.ArrowBack, "Atrás")
                    }
                }
            )
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(recipes) { recipe ->
                PopularRecipeCard(
                    title = recipe.title,
                    calories = recipe.calories,
                    servings = recipe.servings,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }
}

data class Recipe(
    val title: String,
    val calories: String,
    val servings: String
)
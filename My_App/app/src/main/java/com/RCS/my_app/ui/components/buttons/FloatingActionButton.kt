package com.RCS.my_app.ui.components.buttons

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun RecipeFloatingActionButton(
    onAddRecipeClick: () -> Unit,
    onViewRecipesClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    var expanded by remember { mutableStateOf(false) }

    Box(
        modifier = modifier.wrapContentSize(),
        contentAlignment = Alignment.BottomEnd
    ) {
        AnimatedVisibility(
            visible = expanded,
            enter = fadeIn() + expandVertically(),
            exit = fadeOut() + shrinkVertically(),
            modifier = Modifier.align(Alignment.BottomEnd)
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.padding(bottom = 72.dp)
            ) {
                ExtendedFloatingActionButton(
                    onClick = {
                        onViewRecipesClick()
                        expanded = false
                    },
                    icon = { Icon(Icons.Default.List, "Ver recetas") },
                    text = { Text("Ver recetas") },
                    containerColor = MaterialTheme.colorScheme.secondaryContainer
                )

                ExtendedFloatingActionButton(
                    onClick = {
                        onAddRecipeClick()
                        expanded = false
                    },
                    icon = { Icon(Icons.Default.Add, "Añadir receta") },
                    text = { Text("Subir receta") }
                )
            }
        }

        FloatingActionButton(
            onClick = { expanded = !expanded },
            modifier = Modifier.size(56.dp),
            containerColor = if (expanded) MaterialTheme.colorScheme.primaryContainer
            else MaterialTheme.colorScheme.primary
        ) {
            Icon(
                imageVector = if (expanded) Icons.Default.Close else Icons.Default.Add,
                contentDescription = "Menú recetas",
                tint = if (expanded) MaterialTheme.colorScheme.onPrimaryContainer
                else MaterialTheme.colorScheme.onPrimary
            )
        }
    }
}
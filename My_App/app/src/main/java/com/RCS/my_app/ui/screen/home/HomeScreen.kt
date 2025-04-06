package com.RCS.my_app.ui.screen.home

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.RCS.my_app.ui.components.*
import com.RCS.my_app.ui.components.cards.FeaturedRecipeCard
import com.RCS.my_app.ui.components.cards.PopularRecipeCard
import com.RCS.my_app.ui.components.navigation.AppBottomNavBar
import com.RCS.my_app.ui.components.sections.CategorySection
import com.RCS.my_app.ui.components.sections.SectionTitle
import com.RCS.my_app.ui.components.buttons.RecipeFloatingActionButton

@Composable
fun HomeScreen(
    onNavigate: (String) -> Unit,
    currentRoute: String,
    onAddRecipeClick: () -> Unit,
    onViewRecipesClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold(
        bottomBar = { AppBottomNavBar(currentRoute, onNavigate) },
        floatingActionButton = {
            RecipeFloatingActionButton(
                onAddRecipeClick = onAddRecipeClick,
                onViewRecipesClick = onViewRecipesClick
            )
        },
        floatingActionButtonPosition = FabPosition.Center
    ){ paddingValues ->
        LazyColumn(
            modifier = modifier
                .fillMaxSize()
                .padding(paddingValues),
            verticalArrangement = Arrangement.spacedBy(24.dp),
            contentPadding = PaddingValues(bottom = 80.dp)
        ) {
            item {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = "Good Morning",
                        style = MaterialTheme.typography.bodyMedium
                    )
                    Text(
                        text = "Alena Sabyan",
                        style = MaterialTheme.typography.headlineMedium
                    )
                }
            }

            item {
                FeaturedRecipeCard(
                    title = "Asian white noodle with extra seafood",
                    author = "James Spader",
                    time = "20 Min"
                )
            }

            item {
                CategorySection(categories = listOf("Breakfast", "Lunch", "Dinner"))
            }

            item {
                SectionTitle(title = "Popular Recipes", showAll = true)
            }

            items(popularRecipes) { recipe ->
                PopularRecipeCard(
                    title = recipe.title,
                    calories = recipe.calories,
                    servings = recipe.servings,
                    modifier = Modifier.padding(horizontal = 16.dp)
                )
            }
        }
    }
}

private val popularRecipes = listOf(
    Recipe("Healthy Taco Salad with fresh vegetable", "120", "2"),
    Recipe("Japanese-style Pancakes Recipe", "64", "12")
)

data class Recipe(
    val title: String,
    val calories: String,
    val servings: String
)
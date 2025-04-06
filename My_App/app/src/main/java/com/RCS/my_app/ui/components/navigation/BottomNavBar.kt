package com.RCS.my_app.ui.components.navigation

import androidx.compose.foundation.layout.height
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import com.RCS.my_app.R

@Composable
fun AppBottomNavBar(
    currentRoute: String,
    onItemClick: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val items = listOf(
        NavItem("home", R.drawable.ic_home, R.drawable.ic_home_filled),
        NavItem("search", R.drawable.ic_search, R.drawable.ic_search_filled),
        NavItem("notifications", R.drawable.ic_notification, R.drawable.ic_notification_filled),
        NavItem("profile", R.drawable.ic_profile, R.drawable.ic_profile_filled)
    )

    NavigationBar(
        modifier = modifier.height(64.dp),
        containerColor = MaterialTheme.colorScheme.surfaceVariant
    ) {
        items.forEach { item ->
            NavigationBarItem(
                icon = {
                    Icon(
                        imageVector = ImageVector.vectorResource(
                            id = if (currentRoute == item.route) item.selectedIcon else item.unselectedIcon
                        ),
                        contentDescription = item.route
                    )
                },
                label = { Text(item.route.replaceFirstChar { it.uppercase() }) },
                selected = currentRoute == item.route,
                onClick = { onItemClick(item.route) },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = MaterialTheme.colorScheme.primary,
                    unselectedIconColor = MaterialTheme.colorScheme.onSurfaceVariant,
                    selectedTextColor = MaterialTheme.colorScheme.primary,
                    unselectedTextColor = MaterialTheme.colorScheme.onSurfaceVariant,
                    indicatorColor = MaterialTheme.colorScheme.surfaceVariant
                )
            )
        }
    }
}

data class NavItem(
    val route: String,
    val unselectedIcon: Int,
    val selectedIcon: Int
)
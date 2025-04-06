package com.RCS.my_app.ui.components.navigation

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.RCS.my_app.R

@Composable
fun AppBottomNavBar(
    currentRoute: String,
    onItemClick: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val items = listOf(
        NavItem(
            route = "home",
            iconRes = R.drawable.ic_nav_home, // Usa el selector
            contentDescription = "Inicio"
        ),
        NavItem(
            route = "search",
            iconRes = R.drawable.ic_nav_search,
            contentDescription = "Buscar"
        ),
        NavItem(
            route = "notifications",
            iconRes = R.drawable.ic_nav_notifications,
            contentDescription = "Notificaciones"
        ),
        NavItem(
            route = "profile",
            iconRes = R.drawable.ic_nav_profile,
            contentDescription = "Perfil"
        )
    )

    NavigationBar(
        modifier = modifier.height(64.dp),
        containerColor = MaterialTheme.colorScheme.surfaceVariant
    ) {
        items.forEach { item ->
            NavigationBarItem(
                icon = {
                    Icon(
                        painter = painterResource(id = item.iconRes),
                        contentDescription = item.contentDescription,
                        modifier = Modifier.size(24.dp)
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
    val iconRes: Int,          // Ahora usa el selector XML
    val contentDescription: String
)
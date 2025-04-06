package com.RCS.my_app.ui.components.navigation

import androidx.compose.foundation.layout.height
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.RCS.my_app.R

@Composable
fun AppBottomNavBar(
    currentRoute: String,
    onItemClick: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    NavigationBar(
        modifier = modifier.height(64.dp),
        containerColor = MaterialTheme.colorScheme.surfaceVariant
    ) {
        listOf(
            "home" to R.drawable.ic_home,
            "search" to R.drawable.ic_search,
            "notifications" to R.drawable.ic_notification,
            "profile" to R.drawable.ic_profile
        ).forEach { (route, iconRes) ->
            NavigationBarItem(
                icon = { Icon(painterResource(id = iconRes), contentDescription = route) },
                label = { Text(route.replaceFirstChar { it.uppercase() }) },
                selected = currentRoute == route,
                onClick = { onItemClick(route) }
            )
        }
    }
}
// app/ui/components/text/TextLink.kt
package com.RCS.my_app.ui.components.text

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp

@Composable
fun TextLink(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    color: Color = MaterialTheme.colorScheme.primary.copy(alpha = 0.8f)
) {
    Text(
        text = text,
        style = MaterialTheme.typography.bodyMedium.copy(
            color = color,
            textDecoration = TextDecoration.Underline
        ),
        modifier = modifier
            .clickable(onClick = onClick)
            .padding(4.dp)
    )
}
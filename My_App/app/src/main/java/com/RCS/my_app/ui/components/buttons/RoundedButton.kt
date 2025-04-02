package com.RCS.my_app.ui.components.buttons

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun RoundedButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    buttonColor: Color = Color.Blue,
    textColor: Color = Color.White,
    cornerRadius: Int = 8,
    height: Int = 48,
    paddingValues: PaddingValues = PaddingValues(horizontal = 16.dp),
    enabled: Boolean = true
) {
    Button(
        onClick = onClick,
        modifier = modifier
            .height(height.dp),
        shape = RoundedCornerShape(cornerRadius.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = buttonColor,
            contentColor = textColor,
            disabledContainerColor = buttonColor.copy(alpha = 0.5f),
            disabledContentColor = textColor.copy(alpha = 0.5f)
        ),
        contentPadding = paddingValues,
        enabled = enabled
    ) {
        Text(
            text = text,
            fontSize = 16.sp
        )
    }
}
package com.RCS.my_app.ui.components.textlinks

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun TextLink(
    text: String,
    onClick: (() -> Unit)? = null,
    url: String? = null,
    modifier: Modifier = Modifier,
    color: Color = MaterialTheme.colorScheme.primary,
    fontSize: Int = 16,
    underline: Boolean = true,
    paddingValues: PaddingValues = PaddingValues(horizontal = 4.dp, vertical = 2.dp),
    style: TextStyle = LocalTextStyle.current
) {
    val uriHandler = LocalUriHandler.current

    val textStyle = style.merge(
        TextStyle(
            color = color,
            fontSize = fontSize.sp,
            textDecoration = if (underline) TextDecoration.Underline else TextDecoration.None
        )
    )

    val annotatedString = buildAnnotatedString {
        pushStyle(SpanStyle(color = color))
        append(text)
        pop()
    }

    ClickableText(
        text = annotatedString,
        onClick = {
            if (url != null) {
                uriHandler.openUri(url)
            } else {
                onClick?.invoke()
            }
        },
        modifier = modifier,
        style = textStyle
    )
}
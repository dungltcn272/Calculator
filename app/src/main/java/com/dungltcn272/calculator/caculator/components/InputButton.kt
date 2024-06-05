package com.dungltcn272.calculator.caculator.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun InputButton(
    modifier: Modifier = Modifier,
    text: String = "",
    onClick: () -> Unit,
    @DrawableRes icon: Int? = null,
    color: Color = Color.White
) {
    Box(
        modifier = modifier
            .aspectRatio(1f)
            .padding(8.dp)
            .background(color = color, shape = CircleShape)
            .clip(CircleShape)
            .clickable(onClick = onClick)
    ) {
        if (icon != null) {
            Icon(
                painter = painterResource(id = icon),
                contentDescription = "icon $icon",
                modifier = Modifier.align(
                    Alignment.Center
                )
            )
        } else {
            Text(
                text = text,
                color = Color.Black,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.align(
                    Alignment.Center
                )
            )
        }

    }

}

@Preview
@Composable
fun InputButtonPreview() {
    InputButton(onClick = {}, text = "00")
}
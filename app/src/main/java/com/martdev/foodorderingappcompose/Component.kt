package com.martdev.foodorderingappcompose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Tab
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.martdev.foodorderingappcompose.ui.theme.GREY2
import com.martdev.foodorderingappcompose.ui.theme.ORANGE2
import com.martdev.foodorderingappcompose.ui.theme.ORANGE3

@Composable
fun TabItem(text: String,
            selected: Boolean,
            color: Color = Color.Black,
            indicatorWidth: Dp = 134.dp,
            clicked: () -> Unit) {

    Tab(selected = selected, onClick = clicked) {
        Text(text = text,
            Modifier.padding(bottom = 5.dp),
            color = color,
            fontSize = 18.sp,
            fontFamily = fontFamily(R.font.sfprotext_semibold)
        )

        Box(
            Modifier
                .size(width = indicatorWidth, 3.dp)
                .clip(CircleShape)
                .background(
                    color = if (selected) ORANGE2 else Color.Transparent
                )
        )
    }
}

@Composable
fun ButtonComponent(text: String, modifier: Modifier, clicked: () -> Unit) {
    Button(onClick = clicked,
        modifier = modifier.size(314.dp, 70.dp),
        shape = RoundedCornerShape(30.dp),
        colors = ButtonDefaults.buttonColors(backgroundColor = ORANGE3)
    ) {
        Text(text = text,
            color = GREY2,
            fontSize = 17.sp,
            fontFamily = fontFamily(R.font.sfprotext_semibold)
        )
    }
}

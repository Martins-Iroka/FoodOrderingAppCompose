package com.martdev.foodorderingappcompose

import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.sp

fun textStyle() = TextStyle(color = Color.Black,
    fontSize = 17.sp,
    fontFamily = FontFamily(Font(R.font.sfprotext_regular)))

@Composable
fun fieldColor() = TextFieldDefaults.textFieldColors(
    backgroundColor = Color.Transparent,
    cursorColor = Color.Black,
    focusedIndicatorColor = Color.Black,
    unfocusedIndicatorColor = Color.Gray
)

fun fontFamily(fontId: Int) = FontFamily(Font(fontId))
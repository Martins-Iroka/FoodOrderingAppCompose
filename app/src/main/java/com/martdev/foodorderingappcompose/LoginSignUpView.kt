package com.martdev.foodorderingappcompose

import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.animateInt
import androidx.compose.animation.core.animateIntAsState
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.martdev.foodorderingappcompose.ui.theme.FoodOrderingAppComposeTheme
import com.martdev.foodorderingappcompose.ui.theme.GREY
import com.martdev.foodorderingappcompose.ui.theme.GREY2
import com.martdev.foodorderingappcompose.ui.theme.ORANGE2

@Composable
fun LoginSignUpBody(
    onLoginClicked: () -> Unit
) {

    var selected by remember {
        mutableStateOf(0)
    }

    Column(
        Modifier
            .fillMaxSize()
            .background(GREY)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Column(
            Modifier
                .fillMaxWidth()
                .height(382.dp)
                .clip(RoundedCornerShape(bottomStart = 30.dp, bottomEnd = 30.dp))
                .background(Color.White),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Image(painter = painterResource(id = R.drawable.logo2),
                contentDescription = "Logo",
                Modifier
                    .padding(top = 120.dp)
                    .size(150.dp)
            )

            TabRow(selectedTabIndex = selected,
                modifier = Modifier
                    .padding(top = 80.dp),
                backgroundColor = Color.Transparent,
                contentColor = ORANGE2,
                indicator = {
                    TabRowDefaults.Indicator(color = Color.Transparent)
                },
                divider = {
                    TabRowDefaults.Divider(color = Color.Transparent)
                }
            ) {
                TabItem(text = "Login", selected = selected == 0) {
                    selected = 0
                }

                TabItem(text = "Sign up", selected = selected == 1) {
                    selected = 1
                }
            }
        }


        Crossfade(targetState = selected
        ) { selected ->
            when(selected) {
                0 -> LoginBody()
                1 -> SignupBody()
            }
        }

        Button(onClick = { onLoginClicked() },
            Modifier
                .padding(top = 136.dp, bottom = 41.dp)
                .size(314.dp, 70.dp),
            shape = RoundedCornerShape(30.dp),
            colors = ButtonDefaults.buttonColors(backgroundColor = ORANGE2)) {

            Text(text = if (selected == 0) "Login" else "Sign up",
                color = GREY2,
                fontSize = 17.sp,
                fontFamily = fontFamily(R.font.sfprotext_semibold)
            )
        }
    }
}

@Composable
private fun LoginBody() {

    var emailAddress by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Column(
        Modifier.padding(top = 64.dp)
    ) {
        TextField(value = emailAddress,
            onValueChange = {
                emailAddress = it
            },
            Modifier
                .width(314.dp),
            textStyle = textStyle(),
            label = { Text("Email Address", Modifier.alpha(0.4f), color = Color.Black)
            },
            colors = fieldColor())

        TextField(value = password,
            onValueChange = {
                password = it
            },
            Modifier
                .width(314.dp)
                .padding(top = 46.dp),
            textStyle = textStyle(),
            label = { Text("Password", Modifier.alpha(0.4f), color = Color.Black)
            },
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            colors = fieldColor()
        )


        Text("Forgot Password?",
            Modifier.padding(top = 34.dp),
            color = ORANGE2,
            fontSize = 17.sp,
            fontFamily = fontFamily(R.font.sfprotext_semibold)
        )

    }
}

@Composable
private fun SignupBody() {

    var fullName by remember { mutableStateOf("") }
    var emailAddress by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Column(
        Modifier.padding(top = 64.dp)
    ) {
        TextField(value = fullName,
            onValueChange = {
                fullName = it
            },
            Modifier
                .width(314.dp),
            textStyle = textStyle(),
            label = { Text("Full name", Modifier.alpha(0.4f), color = Color.Black)
            },
            colors = fieldColor())

        TextField(value = emailAddress,
            onValueChange = {
                emailAddress = it
            },
            Modifier
                .width(314.dp)
                .padding(top = 30.dp),
            textStyle = textStyle(),
            label = { Text("Email address", Modifier.alpha(0.4f), color = Color.Black)
            },
            colors = fieldColor()
        )

        TextField(value = password,
            onValueChange = {
                password = it
            },
            Modifier
                .width(314.dp)
                .padding(top = 30.dp),
            textStyle = textStyle(),
            label = { Text("Password", Modifier.alpha(0.4f), color = Color.Black)
            },
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            colors = fieldColor()
        )
    }
}

@Preview(showBackground = true)
@Composable
fun LoginSignUpPreview() {
    FoodOrderingAppComposeTheme {
        LoginSignUpBody {

        }
    }
}
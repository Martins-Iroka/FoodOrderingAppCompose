package com.martdev.foodorderingappcompose

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.martdev.foodorderingappcompose.ui.theme.FoodOrderingAppComposeTheme
import com.martdev.foodorderingappcompose.ui.theme.ORANGE
import com.martdev.foodorderingappcompose.ui.theme.ORANGE2


@Composable
fun GetStartedView(
    onClickGetStarted: () -> Unit
) {
    ConstraintLayout(modifier = Modifier
        .fillMaxSize()
        .background(color = ORANGE)
        .verticalScroll(rememberScrollState())
    ) {
        val (logo, text, legos, button) = createRefs()

        Image(painter = painterResource(id = R.drawable.logo),
            contentDescription = "Logo",
            modifier = Modifier
                .size(73.dp)
                .constrainAs(logo) {
                    top.linkTo(parent.top, margin = 56.dp)
                    start.linkTo(parent.start, margin = 49.dp)
                })

        Text(text = "Food for Everyone",
            modifier = Modifier
                .constrainAs(text) {
                    top.linkTo(logo.bottom, 31.dp)
                    start.linkTo(parent.start, 51.dp)
                },
            color = Color.White,
            fontSize = 65.sp,
            fontFamily = fontFamily(R.font.sfpro_heavy)
        )

        Image(
            painterResource(id = R.drawable.legos),
            "Legos",
            modifier = Modifier
                .constrainAs(legos) {
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    top.linkTo(text.bottom)
                }
                .aspectRatio(0.7f)
        )

        Button(onClick = { onClickGetStarted() },
            modifier = Modifier
                .constrainAs(button) {
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    bottom.linkTo(parent.bottom, 36.dp)
                }
                .size(314.dp, 70.dp),
            shape = RoundedCornerShape(30.dp),
            colors = ButtonDefaults.buttonColors(backgroundColor = Color.White)
        ) {
            Text(text = "Get started",
                color = ORANGE2,
                fontSize = 17.sp,
                fontFamily = fontFamily(R.font.sfprotext_semibold)
            )
        }
    }
}

//@Preview(showBackground = true)
//@Composable
//fun DefaultPreview() {
//    FoodOrderingAppComposeTheme {
//        GetStartedView()
//    }
//}
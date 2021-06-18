package com.martdev.foodorderingappcompose

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIos
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.martdev.foodorderingappcompose.ui.theme.GREY2
import com.martdev.foodorderingappcompose.ui.theme.ORANGE3
import com.martdev.foodorderingappcompose.viewmodel.Food

@Composable
fun FoodDetailView(
    food: Food?,
    navigateBack: () -> Unit = {}
) {
    
    Column(modifier = Modifier
        .fillMaxSize()
        .background(GREY2)
        .verticalScroll(rememberScrollState())) {

        Row(
            Modifier
                .fillMaxWidth()
                .padding(start = 41.dp, top = 60.dp, end = 41.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Image(imageVector = Icons.Filled.ArrowBackIos,
                contentDescription = "Arrow back",
                Modifier.clickable { navigateBack() }
            )

            Image(imageVector = Icons.Outlined.FavoriteBorder,
                contentDescription = "Favorite")
        }

        Box(
            Modifier
                .padding(top = 14.dp)
                .align(Alignment.CenterHorizontally)) {

            Image(painter = painterResource(id = food?.food!!),
                contentDescription = "Food",
                modifier = Modifier
                    .size(300.dp))

            Column(
                Modifier
                    .offset(y = 100.dp)
                    .align(Alignment.Center),
                horizontalAlignment = Alignment.CenterHorizontally) {
                Text(text = food.name,
                    color = Color.Black,
                    fontSize = 28.sp,
                    fontFamily = fontFamily(R.font.sfprotext_semibold)
                )

                Text(text = food.price,
                    color = ORANGE3,
                    fontSize = 22.sp,
                    fontFamily = fontFamily(R.font.sfprotext_semibold)
                )
            }
        }

        Text("Delivery info",
            modifier = Modifier.padding(start = 53.dp, top = 43.dp),
            color = Color.Black,
            fontSize = 17.sp,
            fontFamily = fontFamily(R.font.sfprotext_semibold)
        )

        Text("Delivered between monday aug and thursday 20 from 8pm to 91:32 pm",
            modifier = Modifier
                .width(290.dp)
                .padding(start = 53.dp, top = 7.dp)
                .alpha(0.5f),
            color = Color.Black,
            fontSize = 15.sp,
            fontFamily = fontFamily(R.font.sfprotext_regular)
        )

        Text("Return policy",
            modifier = Modifier.padding(start = 53.dp, top = 16.dp),
            color = Color.Black,
            fontSize = 17.sp,
            fontFamily = fontFamily(R.font.sfprotext_semibold)
        )

        Text(
            stringResource(id = R.string.info),
            modifier = Modifier
                .width(290.dp)
                .padding(start = 53.dp, top = 7.dp)
                .alpha(0.5f),
            color = Color.Black,
            fontSize = 15.sp,
            fontFamily = fontFamily(R.font.sfprotext_regular)
        )
        
        ButtonComponent(text = "Add to cart",
            modifier = Modifier
                .padding(top = 50.dp, bottom = 16.dp)
                .align(Alignment.CenterHorizontally)) { println("Add to cart button clicked") }
    }
}
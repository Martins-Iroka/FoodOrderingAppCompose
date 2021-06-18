package com.martdev.foodorderingappcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIos
import androidx.compose.material.icons.filled.ArrowForwardIos
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.martdev.foodorderingappcompose.ui.theme.FoodOrderingAppComposeTheme
import com.martdev.foodorderingappcompose.ui.theme.GREY
import com.martdev.foodorderingappcompose.ui.theme.ORANGE4

@Composable
fun ProfileViewBody(
    clicked: () -> Unit
) {
    Column(
        Modifier
            .fillMaxSize()
            .background(GREY)
            .verticalScroll(rememberScrollState())
    ) {

        Image(imageVector = Icons.Filled.ArrowBackIos,
            contentDescription = "Arrow back",
            modifier = Modifier
                .padding(start = 41.dp, top = 60.dp)
                .clickable { clicked() }
        )
        
        Text(text = "My profile",
            color = Color.Black,
            modifier = Modifier.padding(start = 41.dp, top = 40.dp),
            fontSize = 34.sp,
            fontFamily = fontFamily(R.font.sfprotext_semibold)
        )

        Row(
            Modifier
                .fillMaxWidth()
                .padding(start = 42.dp, top = 44.dp),
            horizontalArrangement = Arrangement.spacedBy(110.dp)
        ) {
            Text(text = "Personal details",
                color = Color.Black,
                fontSize = 18.sp,
                fontFamily = fontFamily(R.font.sfprotext_semibold)
            )

            Text(text = "Change",
                color = ORANGE4,
                fontSize = 15.sp,
                fontFamily = fontFamily(R.font.sfprotext_regular)
            )
        }

        ProfileBody(
            modifier = Modifier
                .padding(top = 11.dp)
                .align(Alignment.CenterHorizontally)
        )

        ActionView(text = "Orders",
            modifier = Modifier
                .padding(top = 27.dp)
                .align(Alignment.CenterHorizontally))


        ActionView(text = "Pending reviews",
            modifier = Modifier
                .padding(top = 27.dp)
                .align(Alignment.CenterHorizontally))

        ActionView(text = "FAQ",
            modifier = Modifier
                .padding(top = 27.dp)
                .align(Alignment.CenterHorizontally))

        ActionView(text = "Help",
            modifier = Modifier
                .padding(top = 27.dp)
                .align(Alignment.CenterHorizontally))
        
        
        ButtonComponent(text = "Update",
            modifier = Modifier
                .padding(top = 59.dp, bottom = 58.dp)
                .align(Alignment.CenterHorizontally)) { }
    }
}

@Composable
fun ProfileBody(modifier: Modifier) {

    Box(modifier = modifier
        .width(315.dp)
        .clip(RoundedCornerShape(20.dp))
        .background(Color.White)
    ) {
        Row(modifier = Modifier.padding(start = 16.dp, top = 18.dp, bottom = 26.dp, end = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(15.dp)
        ) {

            Image(painter = painterResource(id = R.drawable.profile),
                contentDescription = "Profile",
                modifier = Modifier.size(91.dp, 100.dp)
            )
            
            Column(verticalArrangement = Arrangement.spacedBy(7.dp)) {

                Text("Marvis Ighedosa",
                    color = Color.Black,
                    fontSize = 18.sp,
                    fontFamily = fontFamily(R.font.sfprotext_semibold)
                )

                Text("Dosamarvis@gmail.com",
                    color = Color.Black,
                    modifier = Modifier.alpha(0.5f),
                    fontSize = 15.sp,
                    fontFamily = fontFamily(R.font.sfprotext_regular)
                )

                Box(modifier = Modifier
                    .size(width = 165.dp, height = 0.5.dp)
                    .background(Color.Black)
                    .alpha(0.5f)
                )

                Text("+234 9011039271",
                    color = Color.Black,
                    modifier = Modifier.alpha(0.5f),
                    fontSize = 15.sp,
                    fontFamily = fontFamily(R.font.sfprotext_regular)
                )

                Box(modifier = Modifier
                    .size(width = 165.dp, height = 0.5.dp)
                    .background(Color.Black)
                    .alpha(0.5f)
                )

                Text("No 15 uti street off ovie palace road effurun delta state",
                    color = Color.Black,
                    modifier = Modifier
                        .size(width = 182.dp, height = 54.dp)
                        .alpha(0.5f),
                    fontSize = 15.sp,
                    fontFamily = fontFamily(R.font.sfprotext_regular)
                )
            }
        }
    }
}

@Composable
fun ActionView(text: String, modifier: Modifier) {

    Row(modifier = modifier
        .width(315.dp)
        .clip(RoundedCornerShape(20.dp))
        .background(Color.White)
        .padding(start = 23.dp, top = 20.dp, end = 17.dp, bottom = 20.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        
        Text(text = text,
            color = Color.Black,
            fontSize = 18.sp,
            fontFamily = fontFamily(R.font.sfprotext_semibold)
        )

        Image(imageVector = Icons.Filled.ArrowForwardIos,
            contentDescription = "Arrow back",
            modifier = Modifier.size(24.dp)
        )
    }
    
}

@Preview(showBackground = true)
@Composable
fun ProfilePreview() {
    FoodOrderingAppComposeTheme {
        ProfileViewBody {

        }
    }
}
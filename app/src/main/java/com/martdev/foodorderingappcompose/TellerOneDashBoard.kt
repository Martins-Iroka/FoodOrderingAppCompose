package com.martdev.foodorderingappcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.martdev.foodorderingappcompose.ui.theme.DARK_GREY
import com.martdev.foodorderingappcompose.ui.theme.DARK_GREY2
import com.martdev.foodorderingappcompose.ui.theme.FoodOrderingAppComposeTheme

class TellerOneDashBoard : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FoodOrderingAppComposeTheme {

            }
        }
    }
}

@Composable
fun DashboardBody() {
    ConstraintLayout(Modifier.fillMaxSize()) {
        val (toolbar) = createRefs()
//
//        Row(
//            Modifier
//                .fillMaxWidth()
//                .constrainAs(toolbar) {
//                    top.linkTo(parent.top, 26.dp)
//                    start.linkTo(parent.start, 20.dp)
//                    end.linkTo(parent.end, 22.dp)
//                },
//            horizontalArrangement = Arrangement.SpaceEvenly
//        ) {
//
//          Row {
//              Box(modifier = Modifier
//                  .clip(CircleShape)
//                  .background(Color.Black))
//
//              Column(Modifier.padding(start = 12.dp)) {
//                  Text("Hello",
//                      color = DARK_GREY,
//                      fontSize = 12.sp
//                  )
//
//                  Text("Morenikeji ",
//                      color = DARK_GREY2,
//                      fontSize = 14.sp,
//                      fontWeight = FontWeight.Bold
//                  )
//              }
//          }
//
//            Image(imageVector = Icons.Filled.Notifications,
//                contentDescription = "Notification"
//            )
//        }
    }
}

@Preview(showBackground = true)
@Composable
fun DashboardPreview() {
    FoodOrderingAppComposeTheme {
        DashboardBody()
    }
}
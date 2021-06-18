package com.martdev.foodorderingappcompose

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.martdev.foodorderingappcompose.ui.theme.*
import com.martdev.foodorderingappcompose.viewmodel.Food

@Composable
fun HomeViewBody(
    foods: List<Food>,
    navigationItemClicked: (String) -> Unit,
    foodItemClicked: (String) -> Unit,
    cartItemClicked: () -> Unit
) {
    NavigationDrawer(navigationItemClicked)
    HomeBody(
        foods,
        foodItemClicked = {
            foodItemClicked(it)
        },
        cartItemClicked = cartItemClicked
    )
}

@Composable
fun NavigationDrawer(
    itemClicked: (String) -> Unit
) {
    
    Column(
        Modifier
            .fillMaxSize()
            .background(ORANGE3)
    ){
        NavigationItem(resId = R.drawable.ic_profile_icon,
            text = "Profile",
            topPadding = 161.dp, destination = FoodOrderingRoute.PROFILE_VIEW.name) { itemClicked(it) }

        NavigationItem(resId = R.drawable.ic_cart_icon, text = "Order") {}

        NavigationItem(resId = R.drawable.ic_tag_icon, text = "Offer and Promo") {}

        NavigationItem(resId = R.drawable.ic_note, text = "Privacy policy") {}

        NavigationItem(resId = R.drawable.ic_security_icon, text = "Security"){}

        Row(
            Modifier
                .padding(start = 50.dp, bottom = 87.dp)
                .fillMaxHeight(),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalAlignment = Alignment.Bottom
        ) {
            Text(text = "Sign out",
                color = Color.White,
                fontSize = 17.sp,
                fontFamily = fontFamily(R.font.sfprotext_semibold)
            )

            Image(imageVector = Icons.Filled.ArrowForward,
                contentDescription = "Arrow forward",
                colorFilter = ColorFilter.tint(Color.White)
            )
        }
    }
}

@Composable
private fun NavigationItem(resId: Int, text: String,
                           topPadding: Dp = 26.dp,
                           destination: String = "",
                           itemClicked: (String) -> Unit
) {
    Column(
        Modifier
            .fillMaxWidth()
            .padding(start = 38.dp, top = topPadding)
            .clickable { itemClicked(destination) }
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Image(imageVector = ImageVector.vectorResource(id = resId),
                contentDescription = "")

            Text(text,
                color = Color.White,
                fontSize = 17.sp,
                fontFamily = fontFamily(R.font.sfprotext_regular)
            )
        }
        
        Box(modifier = Modifier
            .padding(start = 35.dp, top = 26.dp, bottom = 16.dp)
            .size(132.dp, 0.5.dp)
            .background(GREY5)
        )
    }
}

@Composable
private fun HomeBody(
    foods: List<Food>,
    foodItemClicked: (String) -> Unit,
    cartItemClicked: () -> Unit
) {

    var selected by remember { mutableStateOf(0) }
    var shouldScale by remember { mutableStateOf(false) }
    val offSetAnim by animateDpAsState(targetValue = if (shouldScale) 253.dp else 0.dp)
    val scaleAnim by animateFloatAsState(targetValue = if (shouldScale) 0.6f else 1.0f)

    Column(
        Modifier
            .fillMaxSize()
            .scale(scaleAnim)
            .offset(x = offSetAnim)
            .clip(if (shouldScale) RoundedCornerShape(30.dp) else RoundedCornerShape(0.dp))
            .background(GREY)
            .verticalScroll(rememberScrollState())
    ) {
        Row(
            Modifier
                .fillMaxWidth()
                .padding(top = 55.dp, start = 45.dp, end = 31.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Image(
                imageVector = ImageVector.vectorResource(id = R.drawable.ic_ham),
                contentDescription = "Ham",
                Modifier
                    .clickable { shouldScale = !shouldScale }
                    .padding(15.dp)
            )

            Image(
                imageVector = Icons.Outlined.ShoppingCart,
                contentDescription = "Ham",
                Modifier
                    .padding(15.dp)
                    .size(24.dp)
                    .clickable { cartItemClicked() }
            )
        }

        Text("Delicious\nfood for you",
            Modifier.padding(top = 43.dp, start = 50.dp),
            color = Color.Black,
            fontSize = 34.sp,
            fontFamily = fontFamily(R.font.sfpro_heavy)
        )

        TextField(value = "Search",
            onValueChange = {},
            Modifier
                .alpha(0.5f)
                .width(314.dp)
                .padding(top = 28.dp)
                .clip(RoundedCornerShape(30.dp))
                .align(Alignment.CenterHorizontally)
            ,
            enabled = false,
            textStyle = TextStyle(color = Color.Black,
                fontSize = 17.sp,
                fontFamily = FontFamily(Font(R.font.sfprotext_semibold))
            ),
            leadingIcon = {
                Image(imageVector = ImageVector.vectorResource(id = R.drawable.ic_search),
                    contentDescription = "Search icon"
                )
            },
            shape = RoundedCornerShape(30.dp),
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = GREY3,
                disabledIndicatorColor = Color.Transparent
            )
        )

        ScrollableTabRow(selectedTabIndex = selected,
            modifier = Modifier
                .padding(top = 46.dp),
            backgroundColor = Color.Transparent,
            contentColor = ORANGE2,
            indicator = {
                TabRowDefaults.Indicator(color = Color.Transparent)
            },
            divider = {
                TabRowDefaults.Divider(color = Color.Transparent)
            }
        ) {
            TabItem(text = "Food",
                selected = selected == 0,
                color = showColor(selected == 0),
                indicatorWidth = 87.dp
            ) {
                selected = 0
            }

            TabItem(text = "Drinks",
                selected = selected == 1,
                color = showColor(selected == 1),
                indicatorWidth = 87.dp
            ) {
                selected = 1
            }

            TabItem(text = "Snacks",
                selected = selected == 2,
                color = showColor(selected == 2),
                indicatorWidth = 87.dp
            ) {
                selected = 2
            }

            TabItem(text = "Sauce",
                selected = selected == 3,
                color = showColor(selected == 3),
                indicatorWidth = 87.dp
            ) {
                selected = 3
            }
        }

        Text("See more",
            modifier = Modifier
                .align(Alignment.End)
                .padding(top = 45.dp, end = 41.dp),
            color = ORANGE3,
            fontSize = 15.sp,
            fontFamily = fontFamily(R.font.sfprotext_regular)
        )

        LazyRow(
            contentPadding = PaddingValues(start = 50.dp, top = 64.dp, bottom = 24.dp, end = 50.dp)
        ) {
            items(foods) { food ->
                FoodItemView(food, foodItemClicked)
            }
        }
    }
}

@Composable
private fun FoodItemView(
    food: Food,
    foodItemClicked: (String) -> Unit
) {
    Box(
        Modifier.background(Color.Transparent),
        contentAlignment = Alignment.TopCenter
    ) {
        Column(
            modifier = Modifier
                .clip(RoundedCornerShape(30.dp))
                .background(Color.White)
                .size(220.dp, 270.dp)
                .clickable { foodItemClicked(food.name) },
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(food.name,
                color = Color.Black,
                fontSize = 22.sp,
                fontFamily = fontFamily(R.font.sfprotext_semibold),
                textAlign = TextAlign.Center
                )

            Text("N1,900",
                modifier = Modifier.padding(top = 15.dp, bottom = 39.dp),
                color = ORANGE3,
                fontSize = 17.sp,
                fontFamily = fontFamily(R.font.sfprotext_semibold)
            )
        }

        Image(painter = painterResource(id = food.food),
            contentDescription = "Food",
            Modifier
                .size(250.dp)
                .offset(y = (-45).dp)
        )
    }
}

private fun showColor(selected: Boolean) = if (selected) ORANGE3 else GREY4

@Preview(showBackground = true, device = Devices.PIXEL)
@Composable
fun HomeDefaultPreview() {
    FoodOrderingAppComposeTheme {
       HomeViewBody(
           emptyList(),
           {""},
           {""},
           {}
       )
    }
}
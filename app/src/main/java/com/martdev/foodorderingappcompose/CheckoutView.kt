package com.martdev.foodorderingappcompose

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIos
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.martdev.foodorderingappcompose.ui.theme.FoodOrderingAppComposeTheme
import com.martdev.foodorderingappcompose.ui.theme.GREY
import com.martdev.foodorderingappcompose.ui.theme.ORANGE3
import com.martdev.foodorderingappcompose.ui.theme.ORANGE4

@Composable
fun CheckoutViewBody(
    clicked: () -> Unit
) {

    var proceedToPayment by remember {
        mutableStateOf(false)
    }

    ConstraintLayout(
        Modifier
            .fillMaxSize()
            .background(GREY)
            .verticalScroll(rememberScrollState())
    ) {

        val (arrowBack,
            checkoutText,
            deliveryLayout,
            deliveryMethodText,
            deliveryMethodLayout,
            priceLayout,
            button
        ) = createRefs()

        Image(imageVector = Icons.Filled.ArrowBackIos,
            contentDescription = "Arrow back",
            Modifier.constrainAs(arrowBack) {
                top.linkTo(parent.top, margin = 60.dp)
                start.linkTo(parent.start, margin = 41.dp)
            }.clickable { clicked() }
        )

        Text(
            text = "Checkout",
            color = Color.Black,
            modifier = Modifier.constrainAs(checkoutText) {
                top.linkTo(parent.top, margin = 60.dp)
                centerHorizontallyTo(parent)
            },
            fontSize = 18.sp,
            fontFamily = fontFamily(R.font.sfprotext_semibold)
        )

        if (proceedToPayment) {
            PaymentBody(modifier = Modifier.constrainAs(deliveryLayout) {
                top.linkTo(checkoutText.bottom, 42.dp)
                centerHorizontallyTo(parent)
            })
        } else {
            DeliveryBody(
                Modifier
                    .constrainAs(deliveryLayout) {
                        top.linkTo(checkoutText.bottom, 42.dp)
                        start.linkTo(parent.start, 45.dp)
                    }
            )
        }

        Text(
            text = "Delivery method.",
            color = Color.Black,
            modifier = Modifier.constrainAs(deliveryMethodText) {
                top.linkTo(deliveryLayout.bottom, 42.dp)
                start.linkTo(parent.start, 56.dp)
            },
            fontSize = 17.sp,
            fontFamily = fontFamily(R.font.sfprotext_semibold)
        )

        DeliveryMethodBody(modifier = Modifier.constrainAs(deliveryMethodLayout) {
            top.linkTo(deliveryMethodText.bottom, 20.dp)
            centerHorizontallyTo(parent)
        })

        Row(
            modifier = Modifier.constrainAs(priceLayout) {
                top.linkTo(deliveryMethodLayout.bottom, 67.dp)
                start.linkTo(parent.start, 50.dp)
                end.linkTo(parent.end, 50.dp)
            },
            horizontalArrangement = Arrangement.spacedBy(198.dp)
        ) {

            Text(
                "Total",
                color = Color.Black,
                modifier = Modifier.padding(top = 3.dp),
                fontSize = 17.sp,
                fontFamily = fontFamily(R.font.sfprotext_regular)
            )

            Text(
                "23,000",
                color = Color.Black,
                fontSize = 22.sp,
                fontFamily = fontFamily(R.font.sfprotext_semibold)
            )
        }

        ButtonComponent(text = "Proceed to Payment",
            modifier = Modifier
                .constrainAs(button) {
                    top.linkTo(priceLayout.bottom, 48.dp)
                    centerHorizontallyTo(parent)
                }
                .padding(bottom = 41.dp)) { proceedToPayment = !proceedToPayment }
    }
}

@Composable
fun DeliveryMethodBody(modifier: Modifier) {

    var radioSelected by remember { mutableStateOf(0) }

    Column(modifier =
    modifier
        .size(width = 315.dp, height = 156.dp)
        .clip(RoundedCornerShape(20.dp))
        .background(Color.White)
    ) {
        Column(modifier = Modifier.padding(start = 21.dp, top = 30.dp, bottom = 26.dp),
            verticalArrangement = Arrangement.spacedBy(25.dp)
        ) {
            Row(
                modifier = Modifier.clickable { radioSelected = 0 },
                horizontalArrangement = Arrangement.spacedBy(20.dp)
            ) {

                RadioButton(selected = radioSelected == 0,
                    onClick = null,
                    colors = RadioButtonDefaults.colors(
                        selectedColor = ORANGE3
                    )
                )

                Text(
                    text = "Door delivery",
                    color = Color.Black,
                    fontSize = 17.sp,
                    fontFamily = fontFamily(R.font.sfprotext_regular)
                )
            }

            Box(modifier = Modifier
                .size(width = 232.dp, height = 0.5.dp)
                .padding(start = 50.dp)
                .alpha(0.3f)
                .background(Color.Black)
            )

            Row(
                modifier = Modifier.clickable { radioSelected = 1 },
                horizontalArrangement = Arrangement.spacedBy(20.dp)
            ) {
                RadioButton(selected = radioSelected == 1,
                    onClick = null,
                    colors = RadioButtonDefaults.colors(
                        selectedColor = ORANGE4
                    )
                )

                Text(
                    text = "Pick up",
                    color = Color.Black,
                    fontSize = 17.sp,
                    fontFamily = fontFamily(R.font.sfprotext_regular)
                )
            }
        }
    }
}

@Composable
fun DeliveryBody(modifier: Modifier) {
    Column(
        modifier
    ) {
        Text(text = "Delivery",
            color = Color.Black,
            fontSize = 34.sp,
            fontFamily = fontFamily(R.font.sfprotext_semibold)
        )

        Row(
            Modifier
                .fillMaxWidth()
                .padding(top = 45.dp, bottom = 20.dp),
            horizontalArrangement = Arrangement.spacedBy(121.dp)
        ) {
            Text(text = "Address details",
                color = Color.Black,
                fontSize = 17.sp,
                fontFamily = fontFamily(R.font.sfprotext_semibold)
            )

            Text(text = "Change",
                color = ORANGE4,
                fontSize = 15.sp,
                fontFamily = fontFamily(R.font.sfprotext_regular)
            )
        }

        Column(
            Modifier
                .clip(RoundedCornerShape(20.dp))
                .background(Color.White)
        ) {
            Column(
                Modifier
                    .padding(start = 30.dp, top = 25.dp, end = 53.dp, bottom = 25.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(text = "Marvis Kparobo",
                    color = Color.Black,
                    fontSize = 17.sp,
                    fontFamily = fontFamily(R.font.sfprotext_semibold)
                )
                
                Box(modifier = Modifier
                    .size(width = 232.dp, height = 0.5.dp)
                    .background(Color.Black)
                    .alpha(0.3f)
                )

                Text(text = "Km 5 refinery road opposite re\npublic road, effurun, delta state",
                    color = Color.Black,
                    fontSize = 15.sp,
                    fontFamily = fontFamily(R.font.sfprotext_regular)
                )

                Box(modifier = Modifier
                    .size(width = 232.dp, height = 0.5.dp)
                    .background(Color.Black)
                    .alpha(0.3f)
                )

                Text(text = "+234 9011039271",
                    color = Color.Black,
                    fontSize = 15.sp,
                    fontFamily = fontFamily(R.font.sfprotext_regular)
                )
            }
        }
    }
}

@Composable
fun PaymentBody(modifier: Modifier) {

    var radioSelected by remember { mutableStateOf(0) }

    Column(
        modifier
    ) {
        Text(text = "Payment",
            color = Color.Black,
            fontSize = 34.sp,
            fontFamily = fontFamily(R.font.sfprotext_semibold)
        )

        Text(text = "Payment method",
            color = Color.Black,
            modifier = Modifier.padding(top = 48.dp),
            fontSize = 17.sp,
            fontFamily = fontFamily(R.font.sfprotext_semibold)
        )

        Column(modifier =
        Modifier
            .size(width = 315.dp, height = 205.dp)
            .padding(top = 20.dp)
            .clip(RoundedCornerShape(20.dp))
            .background(Color.White)
        ) {
            Column(modifier = Modifier.padding(start = 21.dp, top = 30.dp, bottom = 26.dp),
                verticalArrangement = Arrangement.spacedBy(15.dp)
            ) {
                Row(
                    modifier = Modifier.clickable { radioSelected = 0 },
                    horizontalArrangement = Arrangement.spacedBy(15.dp)
                ) {

                    RadioButton(selected = radioSelected == 0,
                        onClick = null,
                        modifier = Modifier.offset(y = 4.5.dp),
                        colors = RadioButtonDefaults.colors(
                            selectedColor = ORANGE3
                        )
                    )

                    Image(imageVector = ImageVector.vectorResource(id = R.drawable.ic_card)
                        , contentDescription = "Card",
                        Modifier.size(40.dp)
                    )

                    Text(
                        text = "Card",
                        Modifier.offset(y = 4.5.dp),
                        color = Color.Black,
                        fontSize = 17.sp,
                        fontFamily = fontFamily(R.font.sfprotext_regular)
                    )
                }

                Box(modifier = Modifier
                    .size(width = 232.dp, height = 0.5.dp)
                    .padding(start = 50.dp)
                    .alpha(0.3f)
                    .background(Color.Black)
                )

                Row(
                    modifier = Modifier.clickable { radioSelected = 1 },
                    horizontalArrangement = Arrangement.spacedBy(20.dp)
                ) {
                    RadioButton(selected = radioSelected == 1,
                        onClick = null,
                        Modifier.offset(y = 4.5.dp),
                        colors = RadioButtonDefaults.colors(
                            selectedColor = ORANGE4
                        )
                    )

                    Image(imageVector = ImageVector.vectorResource(id = R.drawable.ic_bank)
                        , contentDescription = "Card",
                        Modifier.size(40.dp)
                    )

                    Text(
                        text = "Pick up",
                        Modifier.offset(y = 4.5.dp),
                        color = Color.Black,
                        fontSize = 17.sp,
                        fontFamily = fontFamily(R.font.sfprotext_regular)
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CheckoutPreview() {
    FoodOrderingAppComposeTheme {
        CheckoutViewBody {

        }
    }
}
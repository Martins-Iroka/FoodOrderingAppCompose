package com.martdev.foodorderingappcompose

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.calculateTargetValue
import androidx.compose.animation.splineBasedDecay
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.awaitFirstDown
import androidx.compose.foundation.gestures.horizontalDrag
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIos
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.input.pointer.positionChange
import androidx.compose.ui.input.pointer.util.VelocityTracker
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.martdev.foodorderingappcompose.ui.theme.FoodOrderingAppComposeTheme
import com.martdev.foodorderingappcompose.ui.theme.GREY
import com.martdev.foodorderingappcompose.ui.theme.ORANGE3
import com.martdev.foodorderingappcompose.viewmodel.Food
import com.martdev.foodorderingappcompose.viewmodel.foods
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlin.math.absoluteValue
import kotlin.math.roundToInt

@Composable
fun CartView(
    foods: List<Food>,
    clicked: () -> Unit,
    onDeleteItem: (Food) -> Unit
) {

    Column(modifier = Modifier
        .fillMaxSize()
        .background(GREY),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(start = 41.dp, top = 60.dp),
            horizontalArrangement = Arrangement.spacedBy(123.dp)
        ) {

            Image(imageVector = Icons.Filled.ArrowBackIos,
                contentDescription = "Arrow back",
                modifier = Modifier.clickable { clicked() }
            )

            Text(text = "Cart ${foods.size}",

                color = Color.Black,
                fontSize = 18.sp,
                fontFamily = fontFamily(R.font.sfprotext_semibold)
            )
        }
        
        Row( modifier = Modifier.padding(top = 61.dp),
            horizontalArrangement = Arrangement.spacedBy(5.dp, Alignment.CenterHorizontally)) {

            Image(imageVector = ImageVector.vectorResource(id = R.drawable.ic_swipe_gesture),
                contentDescription = "Swipe")

            Text(text = "swipe on an item to delete",
                color = Color.Black,
                fontSize = 10.sp,
                fontFamily = fontFamily(R.font.sfprotext_regular)
            )
        }
        
        LazyColumn(contentPadding = PaddingValues(top = 21.dp)) {
            items(items = foods, key = {
                it.name
            }) { food ->
                FoodItemView2(food = food, onDeleteItem)
            }
        }
    }
}


@Composable
fun FoodItemView2(food: Food, removeFood: (Food) -> Unit) {

    val width = 315.dp
    val height = 102.dp
    val size = 100.dp

    Box(modifier = Modifier
        .size(width, height)
        .padding(bottom = 14.dp)
    ) {

        Row(modifier = Modifier
            .size(width, height)
            .swipeToDismiss(food, removeFood)
            .clip(RoundedCornerShape(20.dp))
            .background(Color.White)
            .padding(top = 16.dp)
        ) {

            Image(painter = painterResource(id = food.food),
                contentDescription = "Food",
                modifier = Modifier
                    .size(size)
                    .aspectRatio(0.5f)
                    .offset(y = 10.dp)
            )

            Column(verticalArrangement = Arrangement.spacedBy(11.dp)) {
                Text(food.name,
                    color = Color.Black,
                    fontSize = 17.sp,
                    fontFamily = fontFamily(R.font.sfprotext_semibold)
                )

                Text(food.price,
                    color = ORANGE3,
                    fontSize = 15.sp,
                    fontFamily = fontFamily(R.font.sfprotext_semibold)
                )
            }
        }
    }
}

private fun Modifier.swipeToDismiss(
    food: Food,
    onDismissed: (Food) -> Unit
): Modifier = composed {
    val offsetX = remember { Animatable(0f) }
    pointerInput(Unit) {
        // Used to calculate a settling position of a fling animation.
        val decay = splineBasedDecay<Float>(this)
        // Wrap in a coroutine scope to use suspend functions for touch events and animation.
        coroutineScope {
            while (true) {
                // Wait for a touch down event.
                val pointerId = awaitPointerEventScope { awaitFirstDown().id }
                offsetX.stop() // Add this line
                // Prepare for drag events and record velocity of a fling.
                val velocityTracker = VelocityTracker()
                // Wait for drag events.
                awaitPointerEventScope {
                    horizontalDrag(pointerId) { change ->
                        launch {
                            // Add this line
                            offsetX.snapTo(offsetX.value + change.positionChange().x)
                        }
                        // Record the velocity of the drag.
                        velocityTracker.addPosition(change.uptimeMillis, change.position)
                    }
                }
                // Dragging finished. Calculate the velocity of the fling.
                val velocity = velocityTracker.calculateVelocity().x
                // Add this line
                val targetOffsetX = decay.calculateTargetValue(offsetX.value, velocity)
                offsetX.updateBounds(
                    lowerBound = -size.width.toFloat(),
                    upperBound = size.width.toFloat()
                )
                launch {
                    if (targetOffsetX.absoluteValue <= size.width) {
                        // Not enough velocity; Slide back.
                        offsetX.animateTo(targetValue = 0f, initialVelocity = velocity)
                    } else {
                        // Enough velocity to slide away the element to the edge.
                        offsetX.animateDecay(velocity, decay)
                        // The element was swiped away.
                        onDismissed(food)
                    }
                }
            }
        }
    }
        .offset {
            IntOffset(offsetX.value.roundToInt(), 0)
        }
}

@Preview(showBackground = true, device = Devices.PIXEL)
@Composable
fun CartPreview() {
    FoodOrderingAppComposeTheme {
        CartView(
            foods = foods,
            {},
            { foods[0]}
        )
    }
}

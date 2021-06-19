package com.martdev.foodorderingappcompose

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import androidx.navigation.compose.rememberNavController
import com.martdev.foodorderingappcompose.FoodOrderingRoute.*
import com.martdev.foodorderingappcompose.ui.theme.FoodOrderingAppComposeTheme
import com.martdev.foodorderingappcompose.viewmodel.FoodOrderingVM

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FoodOrderingAppComposeTheme {
                // A surface container using the 'background' color from the theme
               FoodOrderingApp()
            }
        }
    }
}


@Composable
fun FoodOrderingApp() {
    /**
     * This viewModel is tied to the lifecycle of the activity class which host the composable
     */
    val viewModel = viewModel<FoodOrderingVM>()
    val navController = rememberNavController()

    Scaffold {
        FoodOrderingNavHost(navHostController = navController,
            modifier = Modifier.padding(it),
            viewModel = viewModel
        )
    }
}

@Composable
fun FoodOrderingNavHost(
    navHostController: NavHostController,
    modifier: Modifier,
    viewModel: FoodOrderingVM
) {
    NavHost(navController = navHostController,
        startDestination = GET_STARTED_VIEW.name,
        modifier = modifier
    ) {
        composable(GET_STARTED_VIEW.name) {
            GetStartedView {
                navHostController.navigate(LOGIN_SIGNUP_VIEW.name)
            }
        }

        composable(LOGIN_SIGNUP_VIEW.name) {
            LoginSignUpBody {
                navHostController.navigate(HOME_VIEW.name)
            }
        }

        composable(HOME_VIEW.name) { HomeViewBody(
            foods = viewModel.foodList,
            navigationItemClicked = { navHostController.navigate(it) },
            foodItemClicked = { food ->
                navHostController.navigate("${FOOD_DETAIL_VIEW.name}/$food") })
        {
            navHostController.navigate(CART_VIEW.name)
        } }

        composable(
            "${FOOD_DETAIL_VIEW.name}/{food}",
            arguments = listOf(
                navArgument("food") {
                    type = NavType.StringType
                },
            )
        ) { entry ->
            val foodName = entry.arguments?.getString("food")
            val food = viewModel.foodList.find { it.name == foodName }
            FoodDetailView(food = food) { navHostController.navigateUp() }
        }

        composable(CHECKOUT_VIEW.name) { CheckoutViewBody {
            navHostController.navigateUp()
        } }

        composable(CART_VIEW.name) {
            CartView(foods = viewModel.foodList,
                clicked = { navHostController.navigateUp() },
                onDeleteItem = {
                    viewModel.removeFood(it)
                    println("From child 3")
                })
        }

        composable(PROFILE_VIEW.name) { ProfileViewBody {
            navHostController.navigateUp()
        } }
    }
}
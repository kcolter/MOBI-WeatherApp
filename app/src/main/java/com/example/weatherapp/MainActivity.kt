package com.example.weatherapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.runtime.Composable
import com.example.weatherapp.ui.screens.CurrentWeatherUI
import com.example.weatherapp.ui.screens.ThreeDayForecast
import com.example.weatherapp.ui.theme.WeatherAppTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.painterResource
import androidx.lifecycle.viewmodel.compose.viewModel

class MainActivity : ComponentActivity() {

    //define view model
    private lateinit var mainViewModel: MainViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            WeatherAppTheme {

                //init view model
                mainViewModel = viewModel();

                //entry point
                DisplayUI(mainViewModel)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DisplayUI(mainViewModel: MainViewModel){

    //nav controller
    val navController = rememberNavController()

    //selected index for nav bar
    var selectedIndex by remember { mutableIntStateOf(0)}

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text("Halifax, NS")
                },
                //colours for top nav bar
                colors = TopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                    scrolledContainerColor = MaterialTheme.colorScheme.primaryContainer,
                    navigationIconContentColor = MaterialTheme.colorScheme.primary,
                    actionIconContentColor = MaterialTheme.colorScheme.primary
                )
            )
        },
        bottomBar = {
            NavigationBar(
                containerColor = MaterialTheme.colorScheme.primaryContainer,
                contentColor = MaterialTheme.colorScheme.primary
            ) {
                NavigationBarItem(
                    label = { Text("Current Weather") },
                    selected = selectedIndex == 0,
                    onClick = {
                        selectedIndex = 0
                        navController.navigate("current")
                              },
                    icon = { Icon(
                        painter =painterResource(R.drawable.ic_action_today),
                        contentDescription = "current forecast"
                    )},
                )
                NavigationBarItem(
                    label = {Text("3 Day Forecast")},
                    selected = selectedIndex == 1,
                    onClick = {
                        selectedIndex = 1
                        navController.navigate("forecast")
                              },
                    icon = { Icon(
                        painter =painterResource(R.drawable.ic_action_today),
                        contentDescription = "current forecast"
                    )},
                )
            }
        }
    ){
        innerPadding ->

        //getting the weather as state
        val w = mainViewModel.weather.collectAsState().value

            NavHost(
                navController = navController,
                startDestination = "current",
                modifier = Modifier.padding(innerPadding)
            ){
                composable(route = "current"){

                    if (w != null) {
                        CurrentWeatherUI(w.currentWeather)
                    }
                }

                composable(route = "forecast"){
                    if (w != null) {
                        ThreeDayForecast(mainViewModel)
                    }
                }
            }
    }






}

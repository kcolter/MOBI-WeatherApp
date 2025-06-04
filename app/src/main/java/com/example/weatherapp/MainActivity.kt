package com.example.weatherapp

import android.Manifest
import androidx.compose.ui.platform.LocalContext
import com.google.android.gms.location.LocationServices
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
import com.example.weatherapp.ui.screens.MultiDayForecast
import com.example.weatherapp.ui.theme.WeatherAppTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import com.google.android.gms.location.Priority
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
import androidx.media3.common.util.Log
import androidx.media3.common.util.UnstableApi
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import androidx.core.content.ContextCompat
import android.content.pm.PackageManager
import com.google.android.gms.tasks.CancellationTokenSource

class MainActivity : ComponentActivity() {

    //define view model
    private lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        setContent {
            WeatherAppTheme {
                //entry point
                GetLocation()//this function also updates coordinates in our MainViewModel

                //init view model
                mainViewModel = viewModel()

                //call UI
                DisplayUI(mainViewModel)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DisplayUI(mainViewModel: MainViewModel){

    //weather variable to allow collection of value as state
    val weather = mainViewModel.weather.collectAsState().value

    //nav controller
    val navController = rememberNavController()

    //selected index for nav bar
    var selectedIndex by remember { mutableIntStateOf(0)}

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(weather?.location?.name + ", " + weather?.location?.region + ", " + weather?.location?.country)
                },
                //colours for top bar
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
                    label = {Text("Forecast")},
                    selected = selectedIndex == 1,
                    onClick = {
                        selectedIndex = 1
                        navController.navigate("forecast")
                              },
                    icon = { Icon(
                        painter =painterResource(R.drawable.ic_action_forecast),
                        contentDescription = "current forecast"
                    )},
                )
            }
        }
    ){
        innerPadding ->
            NavHost(
                navController = navController,
                startDestination = "current",
                modifier = Modifier.padding(innerPadding)
            ){
                composable(route = "current"){
                    if (weather != null) {
                        CurrentWeatherUI(mainViewModel)
                    }
                }

                composable(route = "forecast"){
                    if (weather != null) {
                        MultiDayForecast(mainViewModel)
                    }
                }
            }
    }
}

//location function copied from d2l
@UnstableApi
@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun GetLocation(
    //inject view model here so we can pass the coordinates to it
    viewModel: MainViewModel = viewModel()
) {
    // Remember the permission state(asking for Fine location)
    val permissionState = rememberPermissionState(Manifest.permission.ACCESS_FINE_LOCATION)

    if (permissionState.status.isGranted) {

        // Get Location
        val currentContext = LocalContext.current
        val fusedLocationClient = LocationServices.getFusedLocationProviderClient(currentContext)

        if (ContextCompat.checkSelfPermission(
                currentContext,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED)
        {
            val cancellationTokenSource = CancellationTokenSource()

            fusedLocationClient.getCurrentLocation(Priority.PRIORITY_HIGH_ACCURACY, cancellationTokenSource.token)
                .addOnSuccessListener { location ->
                    if (location != null) {
                        val lat = location.latitude.toString()
                        val lng = location.longitude.toString()

                        val coordinates = "$lat,$lng"

                        // call a function, like in View Model, to do something with location...
                        //pass coordinates into the viewModel
                        viewModel.updateLocation(coordinates)
                    }
                    else {
                        Log.i("TESTING", "Problem encountered: Location returned null")
                    }
                }
        }
    }
    else {
        // Run a side-effect (coroutine) to get permission. The permission popup.
        LaunchedEffect(permissionState){
            permissionState.launchPermissionRequest()
        }
    }
}

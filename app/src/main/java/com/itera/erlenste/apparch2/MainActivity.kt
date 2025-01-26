package com.itera.erlenste.apparch2

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.itera.erlenste.apparch2.data.model.Vehicle
import com.itera.erlenste.apparch2.ui.theme.AppArch2Theme
import com.itera.erlenste.apparch2.utils.VehicleBrandUtils
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AppArch2Theme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    topBar = { ApplicationTopAppBar("Archapp") }) { innerPadding ->
                    MainScreen(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding),
                        viewModel = viewModel()
                    )
                }
            }
        }
    }
}


@Composable
fun MainScreen(modifier: Modifier, viewModel: VehicleViewModel) {
    val vehicleResponse by viewModel.vehicleResponse.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()
    val error by viewModel.error.collectAsState()

    Column(modifier = modifier.fillMaxSize()) {
        LongBasicDropdownMenu(onValueChange = { viewModel.getVehicleResponse(it) })
        if (isLoading) {
            LoadingComponent()
        }
        if (error?.isNotEmpty() == true) {
            Text("$error")
        }
        if (vehicleResponse != null && vehicleResponse!!.Results.isNotEmpty()) {
            Log.i("MainScreen", "VehicleResponse: $vehicleResponse")
            ResultComponent(modifier = modifier, vehicles = vehicleResponse!!.Results)
        }
    }
}

@Composable
fun ResultComponent(modifier: Modifier, vehicles: List<Vehicle>) {
    LazyVerticalStaggeredGrid(columns = StaggeredGridCells.Fixed(3), modifier = Modifier) {
        items(vehicles) { vehicle ->
            VehicleCard(modifier, vehicle)
        }
    }
}

@Composable
fun VehicleCard(modifier: Modifier, vehicle: Vehicle) {
    Card(modifier = Modifier.padding(8.dp), elevation = CardDefaults.cardElevation(8.dp)) {
        Column(Modifier.padding(8.dp)) {
            Text(
                modifier = Modifier.padding(2.dp),
                text = vehicle.Make_Name,
                fontWeight = FontWeight.Bold
            )
            Text(modifier = Modifier.padding(2.dp), text = vehicle.Model_Name)
        }
    }
}

@Composable
fun LongBasicDropdownMenu(onValueChange: (String) -> Unit) {
    var expanded by remember { mutableStateOf(false) }
    val brands = VehicleBrandUtils().getBrands()

    Box(
        modifier = Modifier
            .padding(16.dp)
    ) {
        ExtendedFloatingActionButton(onClick = { expanded = !expanded }) {
            Text("Velg et merke")
        }
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            brands.forEach { option ->
                DropdownMenuItem(
                    text = { Text(option) },
                    onClick = {
                        onValueChange(option)
                        expanded = false
                    }
                )
            }
        }
    }
}

@Composable
fun LoadingComponent(modifier: Modifier = Modifier) {
    CircularProgressIndicator(
        modifier = Modifier.width(64.dp),
        color = MaterialTheme.colorScheme.secondary,
        trackColor = MaterialTheme.colorScheme.surfaceVariant,
    )
}

@Preview(showBackground = true)
@Composable
fun PreviewResultComponent() {
    val vehicles = listOf(
        Vehicle(1, "Awesome", 10, "CoolModel"),
        Vehicle(2, "IN2000", 20, "SmartModel"),
        Vehicle(3, "WOW", 30, "WowModel"),
        Vehicle(4, "Cool", 40, "Beans")
    )
    AppArch2Theme {
        ResultComponent(
            modifier = Modifier, vehicles = vehicles
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewVehicleCard() {
    val vehicle = Vehicle(1, "Awesome", 10, "CoolModel")
    AppArch2Theme {
        VehicleCard(
            modifier = Modifier, vehicle = vehicle
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewLongBasicDropdownMenu() {
    AppArch2Theme {
        LongBasicDropdownMenu(
            onValueChange = {}
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ApplicationTopAppBar(title: String) {
    TopAppBar(
        title = {
            Text(text = title)
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            titleContentColor = MaterialTheme.colorScheme.primary
        )
    )
}

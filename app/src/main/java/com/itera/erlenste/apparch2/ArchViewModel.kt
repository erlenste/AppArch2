package com.itera.erlenste.apparch2

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.itera.erlenste.apparch2.data.datasource.VehicleDatasource
import com.itera.erlenste.apparch2.data.model.VehicleResponse
import com.itera.erlenste.apparch2.data.repository.VehicleRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ArchViewModel : ViewModel() {

    //TODO: implement viewmodel
    private val vehicleRepository = VehicleRepository(VehicleDatasource())

    private val _vehicleResponse = MutableStateFlow<VehicleResponse?>(null)
    val vehicleResponse: StateFlow<VehicleResponse?> = _vehicleResponse.asStateFlow()


    fun getVehicleResponse(brand: String) {
        viewModelScope.launch {
            _vehicleResponse.value = vehicleRepository.getVehicleResponse(brand)
        }
    }
}
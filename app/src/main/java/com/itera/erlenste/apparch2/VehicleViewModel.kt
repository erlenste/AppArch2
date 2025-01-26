package com.itera.erlenste.apparch2

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.itera.erlenste.apparch2.data.datasource.VehicleDatasource
import com.itera.erlenste.apparch2.data.model.VehicleResponse
import com.itera.erlenste.apparch2.data.repository.VehicleRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class VehicleViewModel @Inject constructor(
    private val vehicleRepository: VehicleRepository
) : ViewModel() {

    private val _vehicleResponse = MutableStateFlow<VehicleResponse?>(null)
    val vehicleResponse: StateFlow<VehicleResponse?> = _vehicleResponse.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error.asStateFlow()


    fun getVehicleResponse(brand: String) {
        _isLoading.value = true
        viewModelScope.launch {
            try {
                _vehicleResponse.value = vehicleRepository.getVehicleResponse(brand)
                _error.value = null
            } catch (e: Exception) {
                _error.value = e.message
            } finally {
                _isLoading.value = false
            }
        }
    }
}
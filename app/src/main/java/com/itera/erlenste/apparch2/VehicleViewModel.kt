package com.itera.erlenste.apparch2

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.itera.erlenste.apparch2.data.model.Vehicle
import com.itera.erlenste.apparch2.data.repository.VehicleRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class VehicleViewModel @Inject constructor(
    private val vehicleRepository: VehicleRepository
) : ViewModel() {

    private val _vehicleUiState = MutableStateFlow<VehicleUiState>(
        VehicleUiState(
            vehicles = emptyList()
        )
    )
    val vehicleUiState: StateFlow<VehicleUiState> = _vehicleUiState.asStateFlow()

    fun getVehicles(brand: String) {
        viewModelScope.launch {
            _vehicleUiState.update {
                it.copy(isLoading = true)
            }
            try {
                val vehicleResponse = vehicleRepository.getVehicleResponse(brand)
                Log.i("VehicleViewModel", "Got vehicles: $vehicleResponse")
                if (vehicleResponse != null) {
                    _vehicleUiState.value =
                        _vehicleUiState.value.copy(
                            vehicles = vehicleResponse.Results,
                            isError = false
                        )
                }
            } catch (e: Exception) {
                _vehicleUiState.update {
                    it.copy(isError = true, errorMessage = e.message ?: "Unknown error")
                }
            } finally {
                _vehicleUiState.update {
                    it.copy(isLoading = false)
                }
            }

        }
    }

    // TODO: implementer viewmodel for å holde på tilstand om bilmodeller
    // TODO: bruk vehicleRepository til å hente data
}

data class VehicleUiState(
    val vehicles: List<Vehicle>,
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val errorMessage: String = ""
)
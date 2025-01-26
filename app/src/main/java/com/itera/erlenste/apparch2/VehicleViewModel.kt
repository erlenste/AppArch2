package com.itera.erlenste.apparch2

import androidx.lifecycle.ViewModel
import com.itera.erlenste.apparch2.data.repository.VehicleRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class VehicleViewModel @Inject constructor(
    private val vehicleRepository: VehicleRepository
) : ViewModel() {

    // TODO: implementer viewmodel for å holde på tilstand om bilmodeller
    // TODO: bruk vehicleRepository til å hente data
}
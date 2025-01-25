package com.itera.erlenste.apparch2.data.repository

import com.itera.erlenste.apparch2.data.datasource.VehicleDatasource
import com.itera.erlenste.apparch2.data.model.VehicleResponse

class VehicleRepository(private val vehicleDatasource: VehicleDatasource) {

    suspend fun getVehicleResponse(brand: String): VehicleResponse? {
        return vehicleDatasource.getVehicleResponse(brand)
    }
}
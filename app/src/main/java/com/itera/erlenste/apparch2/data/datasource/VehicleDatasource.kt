package com.itera.erlenste.apparch2.data.datasource

import android.util.Log
import com.itera.erlenste.apparch2.data.Retrofit
import com.itera.erlenste.apparch2.data.api.VehicleApi
import com.itera.erlenste.apparch2.data.model.VehicleResponse
import retrofit2.Response
import javax.inject.Inject

class VehicleDatasource @Inject constructor() {

    suspend fun getVehicleResponse(brand: String): VehicleResponse? {
        val baseUrl = "https://vpic.nhtsa.dot.gov/api/"
        val vehicleUrl = "vehicles/getmodelsformake/{brand}?format=json"
        val vehicleApi: VehicleApi = Retrofit.getLoggingInstance(baseUrl).create(VehicleApi::class.java)

        Log.i("VehicleDatasource", "Getting vehicles for: $brand")

        val vehicleResponse : Response<VehicleResponse> = vehicleApi.getModels(vehicleUrl.replace("{brand}", brand))
        if (!vehicleResponse.isSuccessful) {
            throw Exception("Failed to get vehicles for: $brand")
        }
        return vehicleResponse.body()
    }


}
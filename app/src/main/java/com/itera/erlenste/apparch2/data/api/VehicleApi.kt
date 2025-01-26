package com.itera.erlenste.apparch2.data.api

import com.itera.erlenste.apparch2.data.model.VehicleResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface VehicleApi {

    @GET
    suspend fun getModels(@Url url: String): Response<VehicleResponse>
}
package com.itera.erlenste.apparch2.data.model

data class VehicleResponse(
    val Count: Int,
    val Message: String,
    val SearchCriteria: String,
    val Results: List<Vehicle>
)
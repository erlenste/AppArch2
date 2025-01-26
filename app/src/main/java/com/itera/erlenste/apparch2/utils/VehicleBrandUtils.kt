package com.itera.erlenste.apparch2.utils

import okhttp3.internal.immutableListOf

open class VehicleBrandUtils {

    open fun getBrands(): List<String> {
        val brands = immutableListOf(
            "",
            "audi",
            "alfa-romeo",
            "bmw",
            "chevrolet",
            "chrysler",
            "citroen",
            "dodge",
            "ferrari",
            "ford",
            "honda",
            "hyundai",
            "jaguar",
            "kia",
            "land rover",
            "lotus",
            "mercedes-benz",
            "maserati",
            "mazda",
            "nissan",
            "opel",
            "subaru",
            "suzuki",
            "tesla",
        )
        return brands
    }
}
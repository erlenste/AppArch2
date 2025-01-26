package com.itera.erlenste.apparch2.utils

import okhttp3.internal.immutableListOf

open class VehicleBrandUtils {

    open fun getBrands(): List<String> {
        val brands = immutableListOf(
            "",
            "audi",
            "alfa romeo",
            "aston martin",
            "bmw",
            "byd",
            "chevrolet",
            "chrysler",
            "citroen",
            "cupra",
            "dacia",
            "dodge",
            "ferrari",
            "fiat",
            "ford",
            "honda",
            "hyundai",
            "jaguar",
            "kia",
            "land rover",
            "lamborghini",
            "lotus",
            "mazda",
            "maserati",
            "mclaren",
            "mercedes-benz",
            "mitsubishi",
            "nissan",
            "peugeot",
            "porsche",
            "opel",
            "subaru",
            "suzuki",
            "tesla",
            "volkswagen",
            "volvo"
        )
        return brands
    }
}
package com.sdos.supermarket.domain.model

data class Product(
    val farmName: String?,
    val farmerId: String,
    val phoneNumber: String?,
    val zipCode: String?,
    val category: String,
    val item: String,
    val business: String?
)
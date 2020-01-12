package com.sdos.supermarket.data.entity

import com.google.gson.annotations.SerializedName

class ProductEntity(
    @SerializedName("farm_name") val farmName: String?,
    @SerializedName("farmer_id") val farmerId: String,
    @SerializedName("phone1") val phoneNumber: String?,
    @SerializedName("zipcode") val zipCode: String?,
    val category: String,
    val item: String,
    val business: String?
)

package com.sdos.supermarket.data.entity

import com.google.gson.annotations.SerializedName

class ProductEntity(
    @SerializedName("farm_name") val farmName: String?,
    @SerializedName("farmer_id") val farmerId: String,
    @SerializedName("phone1") val phoneNumber: String?,
    @SerializedName("zipcode") val zipCode: String?,
    @SerializedName("category") val category: String,
    @SerializedName("item") val item: String,
    @SerializedName("business") val business: String?
)

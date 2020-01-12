package com.sdos.supermarket.data.api

import com.sdos.supermarket.data.entity.ProductEntity
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ProductsApi {

    @GET("/resource/hma6-9xbg.json")
    fun getProduct(
        @Query("category") category: String,
        @Query("item") item: String
    ): Call<List<ProductEntity>>
}

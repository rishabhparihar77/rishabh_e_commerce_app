package com.rishabh.e_commerceapp.data.remote

import com.rishabh.e_commerceapp.data.model.ProductDto
import retrofit2.http.GET
import retrofit2.http.Path

interface ProductApi {

    @GET("b/6927e3ac43b1c97be9c7d3d1?meta=false")
    suspend fun getProducts(): List<ProductDto>

    @GET("b/6927e3ac43b1c97be9c7d3d1?meta=false/{id}")
    suspend fun getProduct(@Path("id") id: Int): ProductDto

}
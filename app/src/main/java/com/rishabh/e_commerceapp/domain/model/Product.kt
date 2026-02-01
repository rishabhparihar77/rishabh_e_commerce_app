package com.rishabh.e_commerceapp.domain.model

data class Product(
    val id: Int,
    val title: String,
    val price: Double,
    val description: String,
    val category: String,
    val image: String,
    val rating: Int? = 0,
    val discount: Int? = 0,
    val like: Boolean? = false,
    val isInCart: Boolean = false
)

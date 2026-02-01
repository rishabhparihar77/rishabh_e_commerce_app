package com.rishabh.e_commerceapp.data.mapper

import com.rishabh.e_commerceapp.data.local.entity.CartEntity
import com.rishabh.e_commerceapp.data.model.ProductDto
import com.rishabh.e_commerceapp.domain.model.Product


fun ProductDto.toDomain(isInCart: Boolean) = Product(
    id, title, price, description, category, image,rating,discount,like, isInCart
)

fun Product.toCartEntity() = CartEntity(
    id, title,price, image
)

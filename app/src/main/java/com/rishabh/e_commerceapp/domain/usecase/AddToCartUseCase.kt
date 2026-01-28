package com.rishabh.e_commerceapp.domain.usecase

import com.rishabh.e_commerceapp.domain.model.Product
import com.rishabh.e_commerceapp.domain.repository.ProductRepository
import javax.inject.Inject

class AddToCartUseCase @Inject constructor(private val repo: ProductRepository) {
    suspend operator fun invoke(product: Product) = repo.addToCart(product)
}
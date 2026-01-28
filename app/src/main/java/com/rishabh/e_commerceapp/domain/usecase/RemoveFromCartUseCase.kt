package com.rishabh.e_commerceapp.domain.usecase

import com.rishabh.e_commerceapp.domain.repository.ProductRepository
import javax.inject.Inject


class RemoveFromCartUseCase @Inject constructor(private val repo: ProductRepository) {
    suspend operator fun invoke(productId: Int) =
        repo.removeFromCart(productId)
}
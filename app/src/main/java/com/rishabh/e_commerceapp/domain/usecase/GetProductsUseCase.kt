package com.rishabh.e_commerceapp.domain.usecase

import com.rishabh.e_commerceapp.domain.repository.ProductRepository
import javax.inject.Inject

class GetProductsUseCase @Inject constructor(private val repo: ProductRepository) {
    operator fun invoke() = repo.getProducts()
}
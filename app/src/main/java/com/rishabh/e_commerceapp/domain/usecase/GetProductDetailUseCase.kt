package com.rishabh.e_commerceapp.domain.usecase

import com.rishabh.e_commerceapp.domain.model.Product
import com.rishabh.e_commerceapp.domain.repository.ProductRepository
import javax.inject.Inject

class GetProductDetailUseCase @Inject constructor(
    private val repository: ProductRepository
) {
    suspend operator fun invoke(id: Int): Product {
        return repository.getProductById(id)
    }
}
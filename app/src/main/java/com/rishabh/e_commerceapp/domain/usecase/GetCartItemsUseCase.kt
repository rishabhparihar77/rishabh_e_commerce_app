package com.rishabh.e_commerceapp.domain.usecase

import com.rishabh.e_commerceapp.data.local.entity.CartEntity
import com.rishabh.e_commerceapp.domain.repository.ProductRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCartItemsUseCase @Inject constructor(
    private val repository: ProductRepository
) {

    operator fun invoke(): Flow<List<CartEntity>> {
        return repository.getCartItems()
    }
}
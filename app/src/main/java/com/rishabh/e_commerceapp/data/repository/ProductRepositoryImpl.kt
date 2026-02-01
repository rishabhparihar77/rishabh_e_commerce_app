package com.rishabh.e_commerceapp.data.repository

import com.rishabh.e_commerceapp.common.Resource
import com.rishabh.e_commerceapp.data.local.dao.CartDao
import com.rishabh.e_commerceapp.data.local.entity.CartEntity
import com.rishabh.e_commerceapp.data.remote.ProductApi
import com.rishabh.e_commerceapp.domain.model.Product
import com.rishabh.e_commerceapp.domain.repository.ProductRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ProductRepositoryImpl (
    private val api: ProductApi,
    private val cartDao: CartDao
) : ProductRepository {

    override fun getProducts(): Flow<Resource<List<Product>>> = flow {
        emit(Resource.Loading())
        try {
            val cartIds = cartDao.getCartItems().first().map { it.id }
            val products = api.getProducts().map {
                Product(
                    id = it.id,
                    title = it.title,
                    price = it.price,
                    description = it.description,
                    category = it.category,
                    image = it.image,
                    rating = it.rating,
                    discount = it.discount,
                    like = it.like,
                    isInCart = cartIds.contains(it.id)
                )
            }
            emit(Resource.Success(products))
        } catch (e: Exception) {
            emit(Resource.Error(e.message ?: "Error"))
        }
    }

    override fun getCartItems() = cartDao.getCartItems()

    override suspend fun addToCart(product: Product) {
        cartDao.insert(
            CartEntity(
                id = product.id,
                title = product.title,
                price = product.price,
                image = product.image
            )
        )
    }

    override suspend fun removeFromCart(productId: Int) {
        cartDao.delete(CartEntity(productId, "", 0.0, ""))
    }

    override suspend fun getProductById(id: Int): Product {
        val dto = api.getProducts().find { it.id == id }
        val cartIds = cartDao.getCartItems().first().map { it.id }

        return Product(
            id = dto?.id?:0,
            title = dto?.title?:"",
            price = dto?.price?:0.0,
            description = dto?.description?:"",
            category = dto?.category?:"",
            image = dto?.image?:"",
            rating = dto?.rating?:1,
            discount = dto?.discount?:0,
            like = dto?.like,
            isInCart = cartIds.contains(dto?.id?:0)
        )
    }
}

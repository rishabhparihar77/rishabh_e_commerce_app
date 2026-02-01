package com.rishabh.e_commerceapp.di

import com.rishabh.e_commerceapp.data.local.dao.CartDao
import com.rishabh.e_commerceapp.data.remote.ProductApi
import com.rishabh.e_commerceapp.data.repository.ProductRepositoryImpl
import com.rishabh.e_commerceapp.domain.repository.ProductRepository
import com.rishabh.e_commerceapp.domain.usecase.AddToCartUseCase
import com.rishabh.e_commerceapp.domain.usecase.GetProductDetailUseCase
import com.rishabh.e_commerceapp.domain.usecase.GetProductsUseCase
import com.rishabh.e_commerceapp.domain.usecase.RemoveFromCartUseCase
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

   /* @Provides
    @Singleton
    fun provideGetProductDetailUseCase(repository: ProductRepository) = GetProductDetailUseCase(repository)
*/
   @Provides
   fun provideMealSearchRepository( api: ProductApi, cartDao: CartDao): ProductRepository {
       return ProductRepositoryImpl(api, cartDao)
   }


    /*@Provides
    @Singleton
    fun provideAddToCartUseCase(repository: ProductRepository) = AddToCartUseCase(repository)

    @Provides
    @Singleton
    fun provideGetProductsUseCase(repository: ProductRepository) = GetProductsUseCase(repository)

    @Provides
    @Singleton
    fun provideRemoveFromCartUseCase(repository: ProductRepository) = RemoveFromCartUseCase(repository)
*/
}

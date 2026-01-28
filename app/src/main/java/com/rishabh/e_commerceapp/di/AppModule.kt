package com.rishabh.e_commerceapp.di

import android.content.Context
import androidx.room.Room
import com.rishabh.e_commerceapp.common.Constants
import com.rishabh.e_commerceapp.data.local.database.AppDatabase
import com.rishabh.e_commerceapp.data.remote.ProductApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideApi(): ProductApi =
        Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ProductApi::class.java)

    @Provides
    fun provideDb(@ApplicationContext ctx: Context) =
        Room.databaseBuilder(ctx, AppDatabase::class.java, "app_db").build()

    @Provides
    fun provideCartDao(db: AppDatabase) = db.cartDao()
}
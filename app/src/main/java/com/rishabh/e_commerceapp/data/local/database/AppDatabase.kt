package com.rishabh.e_commerceapp.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.rishabh.e_commerceapp.data.local.dao.CartDao
import com.rishabh.e_commerceapp.data.local.entity.CartEntity

@Database(entities = [CartEntity::class], version = 1)
abstract class AppDatabase: RoomDatabase() {

    abstract fun cartDao(): CartDao
}
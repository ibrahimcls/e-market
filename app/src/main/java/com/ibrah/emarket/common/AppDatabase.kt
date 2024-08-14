package com.ibrah.emarket.common

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.ibrah.emarket.dao.ProductDao
import com.ibrah.emarket.model.Product

@Database(entities = [Product::class], version = 1)
@TypeConverters(Converter::class)
abstract class AppDatabase :RoomDatabase() {
    abstract fun productDao() : ProductDao
}
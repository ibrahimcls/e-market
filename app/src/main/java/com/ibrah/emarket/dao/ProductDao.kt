package com.ibrah.emarket.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.ibrah.emarket.model.Product

@Dao
interface ProductDao {
    @Query("Select * from product")
    fun getAll(): List<Product>

    @Insert
    fun insert(product: Product)

    @Delete
    fun delete(product: Product)

    @Query("SELECT COUNT(*) > 0 FROM product WHERE id=:productId")
    fun isSavedProduct(productId: String): Boolean
}
package com.ibrah.emarket.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.ibrah.emarket.model.BasketItem

@Dao
interface BasketItemDao {
    @Query("Select * from basketitem")
    fun getAll(): List<BasketItem>

    @Query("Select * from basketitem where id =:basketId limit 1")
    fun getBasket(basketId:String): BasketItem

    @Insert
    fun insert(basketItem: BasketItem)

    @Delete
    fun delete(basketItem: BasketItem)

    @Update
    fun update(basketItem: BasketItem)
}
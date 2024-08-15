package com.ibrah.emarket.service

import com.ibrah.emarket.common.AppDatabase
import com.ibrah.emarket.model.BasketItem
import com.ibrah.emarket.model.Product
import javax.inject.Inject

class BasketItemLocalSource @Inject constructor(private val database: AppDatabase) {
    fun getAllBasketItems(): List<BasketItem> {
        val basketItemDao = database.basketItemDao()
        return basketItemDao.getAll()
    }

    fun getBasketItem(id: String): BasketItem {
        val basketItemDao = database.basketItemDao()
        return basketItemDao.getBasket(id)
    }

    fun deleteBasketItem(basketItem: BasketItem) {
        val basketItemDao = database.basketItemDao()
        basketItemDao.delete(basketItem)
    }

    fun saveBasketItem(basketItem: BasketItem) {
        val basketItemDao = database.basketItemDao()
        basketItemDao.insert(basketItem)
    }

    fun isExist(basketItemId: String): Boolean {
        val basketItemDao = database.basketItemDao()
        return basketItemDao.getAll().any { basketItemId == it.id }
    }

    fun addBasketItem(basketItemId: String, add: Int) {
        val basketItemDao = database.basketItemDao()
        val basket = getBasketItem(basketItemId)
        if (basket.count + add == 0) {
            deleteBasketItem(basket)
        } else {
            basketItemDao.update(BasketItem(basketItemId, basket.product, basket.count + add))
        }
    }
}
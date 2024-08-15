package com.ibrah.emarket.repository

import com.ibrah.emarket.model.BasketItem
import com.ibrah.emarket.model.Product
import com.ibrah.emarket.service.BasketItemLocalSource
import javax.inject.Inject

class BasketItemRepository @Inject constructor(
    private val basketItemLocalSource: BasketItemLocalSource
) {
    fun getBasketItemsFromLocal(): List<BasketItem> {
        return basketItemLocalSource.getAllBasketItems();
    }

    fun deleteBasketItem(basketItem: BasketItem) {
        basketItemLocalSource.deleteBasketItem(basketItem)
    }

    fun addBasketItem(product: Product,add:Int) {
        if (basketItemLocalSource.isExist(product.id)) {
            basketItemLocalSource.addBasketItem(product.id,add)
        } else {
            basketItemLocalSource.saveBasketItem(BasketItem(product.id,product,add))
        }
    }


}
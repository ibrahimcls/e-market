package com.ibrah.emarket.service

import com.ibrah.emarket.common.AppDatabase
import com.ibrah.emarket.model.Product
import javax.inject.Inject

class ProductLocalSource @Inject constructor(private val database:AppDatabase){
    fun getAllProducts() : List<Product>{
        val productDao = database.productDao()
        return productDao.getAll()
    }

    fun deleteProduct(product: Product){
        val productDao = database.productDao()
        productDao.delete(product)
    }

    fun saveProduct(product: Product) {
        val productDao = database.productDao()
        productDao.insert(product)
    }

    fun isSavedProduct(product: Product) : Boolean {
        val productDao = database.productDao()
        return productDao.isSavedProduct(product.id)
    }
}
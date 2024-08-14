package com.ibrah.emarket.repository

import android.telephony.TelephonyManager.UssdResponseCallback
import com.ibrah.emarket.common.IResponseCallBack
import com.ibrah.emarket.model.Product
import com.ibrah.emarket.service.ProductApiSource
import com.ibrah.emarket.service.ProductLocalSource
import javax.inject.Inject

class ProductRepository @Inject constructor(
    private val productApiSource: ProductApiSource,
    private val productLocalSource: ProductLocalSource
) {
    fun getProductsFromNetwork(responseCallback: IResponseCallBack<List<Product>>){
        productApiSource.getAllProducts(responseCallback);
    }

    fun getProductsFromLocal() : List<Product>{
        return productLocalSource.getAllProducts();
    }

    fun deleteProduct(product: Product){
        productLocalSource.deleteProduct(product)
    }

    fun saveProduct(product: Product){
        productLocalSource.saveProduct(product)
    }

    fun isSaveProduct(product: Product): Boolean{
        return productLocalSource.isSavedProduct(product)
    }
}
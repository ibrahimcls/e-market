package com.ibrah.emarket.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ibrah.emarket.common.IResponseCallBack
import com.ibrah.emarket.model.Product
import com.ibrah.emarket.repository.ProductRepository
import com.ibrah.emarket.service.ProductApiSource
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class StarredProductViewModel @Inject constructor(val productRepository: ProductRepository) :
    ViewModel() {
    private val _products = MutableLiveData<List<Product>>()
    val products: LiveData<List<Product>> get() = _products
    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> get() = _errorMessage

    fun getStarredProducts() {
        _products.postValue(productRepository.getProductsFromLocal())
    }
}

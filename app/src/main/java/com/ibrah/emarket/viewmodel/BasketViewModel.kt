package com.ibrah.emarket.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ibrah.emarket.common.IResponseCallBack
import com.ibrah.emarket.model.BasketItem
import com.ibrah.emarket.model.Product
import com.ibrah.emarket.repository.BasketItemRepository
import com.ibrah.emarket.repository.ProductRepository
import com.ibrah.emarket.service.ProductApiSource
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class BasketViewModel @Inject constructor(val basketRepository: BasketItemRepository) :
    ViewModel() {
    private val _basketItems = MutableLiveData<List<BasketItem>>()
    val basketItems: LiveData<List<BasketItem>> get() = _basketItems
    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> get() = _errorMessage

    fun fetchProducts() {
        _basketItems.postValue(basketRepository.getBasketItemsFromLocal())
    }
}

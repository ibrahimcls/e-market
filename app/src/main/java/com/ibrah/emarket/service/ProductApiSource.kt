package com.ibrah.emarket.service

import IProductApiSource
import com.ibrah.emarket.common.IResponseCallBack
import com.ibrah.emarket.common.Message
import com.ibrah.emarket.model.Product
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import javax.inject.Inject

class ProductApiSource @Inject constructor(private val retrofit:Retrofit){


    val apiService: IProductApiSource by lazy {
        retrofit.create(IProductApiSource::class.java)
    }

    fun getAllProducts(responseCallBack: IResponseCallBack<List<Product>>){
        apiService.getProducts().enqueue(object : Callback<List<Product>> {
            override fun onResponse(
                call: Call<List<Product>>,
                response: Response<List<Product>>
            ) {
                if (response.isSuccessful) {
                    response.body()?.let { body ->
                        responseCallBack.success(body)
                    } ?: run {
                        responseCallBack.failure(Message.responseBodyIsEmpty)

                    }
                } else {
                    responseCallBack.failure(Message.fail)
                }
            }

            override fun onFailure(call: Call<List<Product>>, t: Throwable) {
                println("API call failed: ${t.message}")
            }
        })
    }
}

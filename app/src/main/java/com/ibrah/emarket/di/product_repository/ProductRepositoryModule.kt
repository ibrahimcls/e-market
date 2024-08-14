package com.ibrah.emarket.di.product_repository

import android.content.Context
import com.ibrah.emarket.di.api_source.ApiSourceComponent
import com.ibrah.emarket.di.api_source.ApiSourceModule
import com.ibrah.emarket.di.local_source.LocalSourceComponent
import com.ibrah.emarket.repository.ProductRepository
import com.ibrah.emarket.di.api_source.DaggerApiSourceComponent
import com.ibrah.emarket.di.local_source.DaggerLocalSourceComponent
import com.ibrah.emarket.di.local_source.LocalSourceModule
import com.ibrah.emarket.service.ProductApiSource
import com.ibrah.emarket.service.ProductLocalSource
import dagger.Module
import dagger.Provides

@Module
class ProductRepositoryModule(private val context : Context){
    @Provides
    fun provideProductRepository() : ProductRepository {
        val localSourceComponent: LocalSourceComponent = DaggerLocalSourceComponent.builder()
            .localSourceModule(LocalSourceModule(context))
            .build()
        val productLocalSource = ProductLocalSource(localSourceComponent.provideAppDatabase())
        val apiSourceComponent: ApiSourceComponent = DaggerApiSourceComponent.builder()
            .apiSourceModule(ApiSourceModule())
            .build()
        val productApiSource = ProductApiSource(apiSourceComponent.provideRetrofit())
        return ProductRepository(productApiSource,productLocalSource)
    }
}
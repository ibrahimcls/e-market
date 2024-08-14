package com.ibrah.emarket.di.product_repository

import com.ibrah.emarket.repository.ProductRepository
import dagger.Component

@Component(modules = [ProductRepositoryModule::class])
interface ProductRepositoryComponent {
    fun provideProductRepository(): ProductRepository
}
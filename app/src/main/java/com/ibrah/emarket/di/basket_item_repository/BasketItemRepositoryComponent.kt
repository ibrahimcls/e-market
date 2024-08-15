package com.ibrah.emarket.di.basket_item_repository

import com.ibrah.emarket.repository.BasketItemRepository
import dagger.Component

@Component(modules = [BasketItemRepositoryModule::class])
interface BasketItemRepositoryComponent {
    fun provideBasketItemRepository(): BasketItemRepository
}
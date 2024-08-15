package com.ibrah.emarket.di.basket_item_repository

import android.content.Context
import com.ibrah.emarket.di.local_source.LocalSourceComponent
import com.ibrah.emarket.di.local_source.DaggerLocalSourceComponent
import com.ibrah.emarket.di.local_source.LocalSourceModule
import com.ibrah.emarket.repository.BasketItemRepository
import com.ibrah.emarket.service.BasketItemLocalSource
import dagger.Module
import dagger.Provides

@Module
class BasketItemRepositoryModule(private val context : Context){
    @Provides
    fun provideBasketItemRepository() : BasketItemRepository {
        val localSourceComponent: LocalSourceComponent = DaggerLocalSourceComponent.builder()
            .localSourceModule(LocalSourceModule(context))
            .build()
        val basketItemLocalSource = BasketItemLocalSource(localSourceComponent.provideAppDatabase())
        return BasketItemRepository(basketItemLocalSource)
    }
}
package com.ibrah.emarket.di.local_source

import com.ibrah.emarket.common.AppDatabase
import dagger.Component

@Component(modules=[LocalSourceModule::class])
interface LocalSourceComponent {
    fun provideAppDatabase(): AppDatabase
}
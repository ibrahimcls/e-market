package com.ibrah.emarket.di.api_source

import dagger.Component
import retrofit2.Retrofit

@Component(modules = [ApiSourceModule::class])
interface ApiSourceComponent {
    fun provideRetrofit():Retrofit
}
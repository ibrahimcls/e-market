package com.ibrah.emarket.di.local_source

import android.content.Context
import androidx.room.Room
import com.ibrah.emarket.common.AppDatabase
import dagger.Module
import dagger.Provides

@Module
class LocalSourceModule(private val context: Context) {
    @Provides
    fun provideAppDatabase(): AppDatabase {
        return Room.databaseBuilder(context, AppDatabase::class.java,"emarket-db")
            .allowMainThreadQueries()
            .build()
    }
}
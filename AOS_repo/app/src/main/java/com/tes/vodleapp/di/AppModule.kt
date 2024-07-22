package com.tes.vodleapp.di

import android.content.SharedPreferences
import com.tes.domain.TokenManager
import com.tes.vodleapp.util.TokenManagerImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Singleton
    @Provides
    fun provideTokenManager(
        sharedPreferences: SharedPreferences
    ): TokenManager = TokenManagerImpl(sharedPreferences)
}

package com.tes.vodleapp.di

import com.tes.domain.repository.UserRepository
import com.tes.domain.repository.VodleRepository
import com.tes.vodleapp.repository.UserRepositoryImpl
import com.tes.vodleapp.repository.VodleRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindUserRepository(userRepositoryImpl: UserRepositoryImpl): UserRepository

    @Binds
    @Singleton
    abstract fun bindVodleRepository(vodleRepositoryImpl: VodleRepositoryImpl): VodleRepository
}

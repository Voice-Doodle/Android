package com.tes.vodleapp.di

import com.tes.vodleapp.datasource.user.UserDataSource
import com.tes.vodleapp.datasource.user.UserDataSourceImpl
import com.tes.vodleapp.datasource.vodle.VodleDataSource
import com.tes.vodleapp.datasource.vodle.VodleDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DataSourceModule {
    @Binds
    @Singleton
    abstract fun bindUserDataSource(userDataSourceImpl: UserDataSourceImpl): UserDataSource

    @Binds
    @Singleton
    abstract fun bindVodleDataSource(vodleDataSourceImpl: VodleDataSourceImpl): VodleDataSource
}

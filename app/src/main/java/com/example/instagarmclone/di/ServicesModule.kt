package com.example.instagarmclone.di

import com.example.instagarmclone.data.AccountServiceImpl
import com.example.instagarmclone.data.StorageServiceImpl
import com.example.instagarmclone.domain.services.AccountService
import com.example.instagarmclone.domain.services.StorageService
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
abstract class ServicesModule {
    @Binds
    abstract fun provideAccountService(impl: AccountServiceImpl): AccountService

    @Binds
    abstract fun provideStorageService(impl:StorageServiceImpl):StorageService
}
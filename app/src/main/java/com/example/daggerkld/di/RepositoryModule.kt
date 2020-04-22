package com.example.daggerkld.di

import com.example.daggerkld.repository.DataRepository
import com.example.daggerkld.repository.DataRepositoryImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {
    @Provides
    @Singleton
    fun provideDataRepository(): DataRepository {
        return DataRepositoryImpl()
    }
}
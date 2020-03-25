package com.example.daggerkld.di

import com.example.daggerkld.repository.DataRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {
    @Provides
    @Singleton
    fun provideDataRepository(): DataRepository = DataRepository()
}
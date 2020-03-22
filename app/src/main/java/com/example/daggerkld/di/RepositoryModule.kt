package com.example.daggerkld.di

import com.example.daggerkld.data.DataRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {
    @Provides
    @Singleton
    fun provideDataMock(): DataRepository = DataRepository()
}
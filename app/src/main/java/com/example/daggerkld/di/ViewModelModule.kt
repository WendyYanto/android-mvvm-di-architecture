package com.example.daggerkld.di

import com.example.daggerkld.repository.DataRepository
import com.example.daggerkld.viewmodel.MainViewModel
import com.example.daggerkld.viewmodel.MainViewModelImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ViewModelModule {
    @Provides
    @Singleton
    fun provideMainViewModel(dataRepository: DataRepository): MainViewModel =
        MainViewModelImpl(dataRepository)
}
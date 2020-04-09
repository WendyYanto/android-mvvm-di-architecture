package com.example.daggerkld.di

import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module

@Module
interface ViewModelProviderModule {

    @Binds
    fun bindsViewModelProvider(factory: ViewModelProviderFactory): ViewModelProvider.Factory

}
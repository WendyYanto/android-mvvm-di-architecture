package com.example.daggerkld.di

import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module

@Module
abstract class ViewModelProviderModule {

    @Binds
    abstract fun bindsViewModelProvider(factory: ViewModelProviderFactory): ViewModelProvider.Factory

}
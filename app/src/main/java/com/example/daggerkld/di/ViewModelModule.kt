package com.example.daggerkld.di

import androidx.lifecycle.ViewModel
import com.example.daggerkld.repository.DataRepository
import com.example.daggerkld.viewmodel.MainViewModel
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap

@Module
class ViewModelModule {

    @Provides
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    fun provideMainViewModel(dataRepository: DataRepository): ViewModel {
        return MainViewModel(dataRepository)
    }
}
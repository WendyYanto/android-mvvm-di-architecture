package com.example.daggerkld.di

import com.example.daggerkld.MainActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, ViewModelModule::class, RepositoryModule::class])
interface AppComponent {
    fun inject(mainActivity: MainActivity)
}
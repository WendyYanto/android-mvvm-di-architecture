package com.example.daggerkld.di

import com.example.daggerkld.MainActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, PresenterModule::class])
interface AppComponent {
    fun inject(mainActivity: MainActivity)
}
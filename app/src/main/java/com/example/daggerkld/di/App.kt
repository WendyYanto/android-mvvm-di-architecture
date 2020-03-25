package com.example.daggerkld.di

import android.app.Application

class App: Application() {

    lateinit var applicationComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        applicationComponent = DaggerAppComponent.builder()
            .application(this)
            .build()
    }
}
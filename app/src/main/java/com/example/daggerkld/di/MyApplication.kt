package com.example.daggerkld.di;

import android.app.Application;

class MyApplication: Application() {

    lateinit var applicationComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        applicationComponent = DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .build()
    }
}
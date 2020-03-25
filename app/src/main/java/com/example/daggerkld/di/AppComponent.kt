package com.example.daggerkld.di

import android.app.Application
import com.example.daggerkld.MainActivity
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton


@Singleton
@Component(modules = [AppModule::class, ViewModelModule::class, RepositoryModule::class])
interface AppComponent {
    fun inject(mainActivity: MainActivity)
    
    @Component.Builder
    interface Builder {
        fun build(): AppComponent
        @BindsInstance
        fun application(application: Application): Builder
    }
}
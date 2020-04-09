@file:Suppress("UNCHECKED_CAST")

package com.example.daggerkld.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.lang.RuntimeException
import javax.inject.Inject
import javax.inject.Provider
import javax.inject.Singleton

@Singleton
class ViewModelProviderFactory @Inject constructor(
    private val providerMap: Map<Class<out ViewModel>, @JvmSuppressWildcards Provider<ViewModel>>
) :
    ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return try {
            providerMap[modelClass]?.get() as T
        } catch (e: Exception) {
            throw RuntimeException(e)
        }
    }
}
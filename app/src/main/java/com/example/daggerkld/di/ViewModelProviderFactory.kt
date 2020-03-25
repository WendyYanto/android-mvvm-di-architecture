@file:Suppress("UNCHECKED_CAST")

package com.example.daggerkld.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import javax.inject.Inject

class ViewModelProviderFactory @Inject constructor(private val providerMap: Map<Class<out ViewModel>, ViewModel>) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return providerMap[modelClass] as T
    }

}
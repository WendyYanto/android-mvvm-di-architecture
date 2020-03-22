package com.example.daggerkld.viewmodel

import androidx.lifecycle.LiveData

interface MainViewModel {
    fun fetchData(): LiveData<List<String>>
    fun removeData(index: Int)
    fun addData(value: String)
}
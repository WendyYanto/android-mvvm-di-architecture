package com.example.daggerkld.viewmodel

import androidx.lifecycle.LiveData
import com.example.daggerkld.data.Response

interface MainViewModel {
    fun fetchData(): LiveData<Response<List<String>>>
    fun removeData(index: Int)
    fun addData(value: String)
}
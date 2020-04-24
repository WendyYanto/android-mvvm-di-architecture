package com.example.daggerkld.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.daggerkld.data.Response
import com.example.daggerkld.repository.DataRepository
import javax.inject.Inject

typealias ResponseData = Response<List<String>>

class MainViewModel @Inject constructor(
    private val repository: DataRepository
) : ViewModel() {

    private val data: MutableLiveData<ResponseData> by lazy {
        MutableLiveData<ResponseData>().apply {
            value = Response.success(repository.get())
        }
    }

    fun removeData(index: Int) {
        try {
            repository.remove(index)
            data.postValue(Response.success(repository.get()))
        } catch (e: Exception) {
            data.postValue(Response.error(e.message ?: "Unspecified Error"))
        }
    }

    fun fetchData(): LiveData<ResponseData> {
        return data
    }

    fun addData(value: String) {
        repository.add(value)
        data.postValue(Response.success(repository.get()))
    }

    fun updateDataPosition(from: Int, to: Int) {
        val size = repository.get().size
        if (from in 0..size && to in 0..size) {
            repository.move(from, to)
            data.postValue(Response.success(repository.get()))
        } else {
            data.postValue(Response.error("Invalid index"))
        }
    }
}
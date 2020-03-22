package com.example.daggerkld.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.daggerkld.data.DataRepository
import javax.inject.Inject

class MainViewModelImpl @Inject constructor(
    private val repository: DataRepository) : MainViewModel, ViewModel() {

    private val data: MutableLiveData<List<String>> by lazy {
        MutableLiveData<List<String>>().also {
            it.value = repository.get()
        }
    }

    override fun removeData(index: Int) {
        repository.remove(index)
        data.value = repository.get()
    }

    override fun fetchData(): LiveData<List<String>> {
        return data
    }

    override fun addData(value: String) {
        repository.add(value)
        data.value = repository.get()
    }
}
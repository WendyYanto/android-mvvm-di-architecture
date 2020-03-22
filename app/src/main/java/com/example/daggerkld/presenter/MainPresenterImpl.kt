package com.example.daggerkld.presenter

import com.example.daggerkld.data.DataRepository
import javax.inject.Inject

class MainPresenterImpl @Inject constructor(
    private val repository: DataRepository) : MainPresenter {

    override fun removeData(index: Int) {
        repository.remove(index)
    }

    override fun fetchData(): List<String> {
        return repository.get()
    }
}
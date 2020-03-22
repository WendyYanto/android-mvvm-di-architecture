package com.example.daggerkld.presenter

interface MainPresenter {
    fun fetchData(): List<String>
    fun removeData(index: Int)
}
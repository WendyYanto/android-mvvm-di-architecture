package com.example.daggerkld.data

class DataRepository {

    private val data: MutableList<String> = ArrayList()

    init {
        this.data.add("First Data")
        this.data.add("Second Data")
    }

    fun add(value: String) {
        this.data.add(value)
    }

    fun remove(index: Int) {
        if (index < 0 || index >= this.data.size) {
            throw IllegalAccessException("Index Is Invalid")
        }
        this.data.removeAt(index)
    }

    fun get(): List<String> {
        return this.data
    }
}
package com.example.daggerkld.repository

class DataRepositoryImpl : DataRepository {

    private val data: MutableList<String> = ArrayList()

    init {
        this.data.add("First Data")
        this.data.add("Second Data")
    }

    override fun add(value: String) {
        this.data.add(value)
    }

    override fun remove(index: Int) {
        if (index < 0 || index >= this.data.size) {
            throw IllegalAccessException("Index Is Invalid")
        }
        this.data.removeAt(index)
    }

    override fun get(): List<String> {
        return this.data
    }

    override fun move(from: Int, to: Int) {
         this.data.removeAt(from).apply {
             data.add(to, this)
         }
    }
}
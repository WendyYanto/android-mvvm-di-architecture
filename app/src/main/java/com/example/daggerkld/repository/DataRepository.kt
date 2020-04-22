package com.example.daggerkld.repository

interface DataRepository {
    fun add(value: String)
    fun remove(index: Int)
    fun get(): List<String>

}
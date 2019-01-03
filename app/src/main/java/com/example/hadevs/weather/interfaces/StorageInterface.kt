package com.example.hadevs.weather.interfaces

interface StorageInterface {
    fun <T> load(type: Class<T>): ArrayList<T>
    fun <T> save(data: T)
}
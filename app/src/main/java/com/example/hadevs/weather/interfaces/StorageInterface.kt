package com.example.hadevs.weather.interfaces

interface StorageInterface {
    fun <T> load(key: String): T
    fun <T> save(key: String, data: T)
    fun <T> addIfCan(key: String, data: T)
}
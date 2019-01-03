package com.example.hadevs.weather.data_providers

class GenericDataProvider<T> {
    private var arrayData: ArrayList<T> = ArrayList()

    fun data(): ArrayList<T> = arrayData

    fun dataCount(): Int = arrayData.size

    fun update(data: Array<T>) {
        arrayData = data.toCollection(ArrayList())
    }

    fun get(index: Int): T {
        return data()[index]
    }
}

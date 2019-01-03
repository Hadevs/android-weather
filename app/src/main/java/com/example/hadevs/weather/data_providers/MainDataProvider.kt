package com.example.hadevs.weather.data_providers

import com.example.hadevs.weather.help_classes.storage
import com.example.hadevs.weather.models.ApixuSearchResponse

class MainDataProvider {
    private var cities: ArrayList<String> = ArrayList()

    fun data(): ArrayList<String> = cities

    fun updateData() {
        val savedStorageData = storage.load<ArrayList<ApixuSearchResponse>>("savedCities")
        if (savedStorageData != null) {
            cities = savedStorageData.asSequence().map{ it.name.toString() }.toCollection(ArrayList())
        }
    }

    fun getElement(index: Int): ApixuSearchResponse? {
        val savedStorageData = storage.load<ArrayList<ApixuSearchResponse>>("savedCities")
        if (savedStorageData != null) {
            return savedStorageData[index]
        }

        return null
    }

    fun update(index: Int, value: String) {
        cities[index] = value
    }

    fun dataCount(): Int = cities.size
}

package com.example.hadevs.weather.data_providers

class MainDataProvider {
    private val cities: ArrayList<String> = ArrayList()

    init {
        fillDemoArray()
    }

    private fun fillDemoArray() {
        cities.add("Saints-petersburg")
    }

    fun data(): ArrayList<String> = cities

    fun dataCount(): Int = cities.size
}

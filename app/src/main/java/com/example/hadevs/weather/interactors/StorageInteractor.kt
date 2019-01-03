package com.example.hadevs.weather.interactors

import com.example.hadevs.weather.interfaces.StorageInterface

@Suppress("UNCHECKED_CAST")
final class StorageInteractor: StorageInterface {
    private var allData: ArrayList<Any> = ArrayList()

    private fun <T> cast(from: Any): T? = from as? T

    override fun <T> load(type: Class<T>): ArrayList<T> {
        val result: ArrayList<T> = ArrayList()
        for (element in allData) {
            if (element::class == type) {
                val castedObject = cast<T>(element)
                castedObject?.let { unboxElement ->
                    result.add(unboxElement)
                }
            }
        }

        return result
    }

    override fun <T> save(data: T) {
        allData.add(data!!)
    }
}
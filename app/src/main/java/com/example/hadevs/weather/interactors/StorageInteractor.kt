package com.example.hadevs.weather.interactors

import com.example.hadevs.weather.interfaces.StorageInterface
import io.paperdb.Paper

@Suppress("UNCHECKED_CAST")
final class StorageInteractor: StorageInterface {

    override fun <T> load(key: String): T {
        return Paper.book().read<T>(key)
    }

    override fun <T> save(key: String, data: T) {
        Paper.book().write(key, data)
    }

    override fun <T> addIfCan(key: String, data: T) {
        val loadedData = load<T>(key)
        val array = loadedData as? ArrayList<T> ?: ArrayList()
        array.add(data)
        save(key, array)
    }
}

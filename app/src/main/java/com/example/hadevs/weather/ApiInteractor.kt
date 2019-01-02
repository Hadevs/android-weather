package com.example.hadevs.weather

import com.beust.klaxon.Klaxon
import com.example.hadevs.weather.models.ApixuCurrentResponse
import org.jetbrains.anko.doAsync
import java.net.URL

class ApiInteractor<T> {
    enum class HTTPType {
        GET
    }
    companion object {
        inline fun <reified T> request(type: HTTPType, url: URL, crossinline callback: (T) -> Int) =
            when (type) {
                HTTPType.GET -> this.getRequest(url, callback)
            }

        inline fun <reified T> getRequest(url: URL, crossinline callback: (T) -> Int) {
            doAsync {
                doAsync {
                    val result = url.readText()
                    // we are in async thread now
                    val model = Klaxon().parse<T>(result)
                    model?.let {
                        callback(it)
                    }
                }
            }
        }
    }
}
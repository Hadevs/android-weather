package com.example.hadevs.weather.interactors

import com.beust.klaxon.Klaxon
import com.example.hadevs.weather.help_classes.ItemClosure
import com.example.hadevs.weather.models.ApixuCurrentResponse
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import java.net.URL

class ApiInteractor<T> {
    enum class HTTPType {
        GET
    }
    companion object {
        inline fun <reified T> request(type: HTTPType, url: URL, crossinline callback: ItemClosure<T>) =
            when (type) {
                HTTPType.GET -> getRequest(
                    url,
                    callback
                )
            }

        inline fun <reified T> getRequest(url: URL, crossinline callback: ItemClosure<T>) {
            doAsync {
                val result = url.readText()
                // we are in async thread now
                val model = Klaxon().parse<T>(result)
                model?.let { unboxmodel ->
                    uiThread {
                        callback(unboxmodel)
                    }
                }
            }
        }
    }
}
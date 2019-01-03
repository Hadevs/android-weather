package com.example.hadevs.weather.managers

import com.example.hadevs.weather.help_classes.ItemClosure
import com.example.hadevs.weather.interactors.ApiInteractor
import com.example.hadevs.weather.models.ApixuCurrentResponse
import com.example.hadevs.weather.models.ApixuSearchResponse
import java.net.URL

class ApixuManager {
    companion object {
        private const val apixuHostString: String = "http://api.apixu.com/v1/"
        private const val apiKey: String = "c7825a578dd248cca3f00522190201"
        fun loadCurrentModel(city: String, callback: ItemClosure<ApixuCurrentResponse>) {
            ApiInteractor.request<ApixuCurrentResponse>(ApiInteractor.HTTPType.GET, URL(apixuHostString + "current.json?key=" + apiKey + "&q=" + city)) {
                callback(it)
            }
        }

        fun searchCities(text: String, callback: ItemClosure<Array<ApixuSearchResponse>>) {
            ApiInteractor.requestArray<ApixuSearchResponse>(ApiInteractor.HTTPType.GET, URL(apixuHostString + "search.json?key=" + apiKey + "&q=" + text)) {
                callback(it.toTypedArray())
            }
        }
    }
}
package com.example.hadevs.weather.activities

import android.annotation.SuppressLint
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import com.example.hadevs.weather.R
import com.example.hadevs.weather.adapters.MainAdapter
import com.example.hadevs.weather.data_providers.GenericDataProvider
import com.example.hadevs.weather.help_classes.storage
import com.example.hadevs.weather.managers.ApixuManager
import com.example.hadevs.weather.models.ApixuSearchResponse
import io.reactivex.subjects.PublishSubject
import kotlinx.android.synthetic.main.activity_search_place.*
import java.util.concurrent.TimeUnit

class SearchPlaceActivity : AppCompatActivity() {

    private val dataProvider = GenericDataProvider<ApixuSearchResponse>()
    private var adapter: MainAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_place)
        val actionBar = supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(true)
        overridePendingTransition(
            R.anim.slide_in,
            R.anim.slide_out
        ) // slide animation
        configureRecycleView()
        startObservingCityField()
    }
    private var subject: PublishSubject<String>? = null

    @SuppressLint("CheckResult")
    private fun startObservingCityField() {

        subject = PublishSubject.create()
        subject?.debounce(1000, TimeUnit.MILLISECONDS)?.subscribe {
            ApixuManager.searchCities(it) { locations ->
                dataProvider.update(locations)
                adapter?.notifyDataSetChanged()
                configureRecycleView()
            }
        }

        searchField.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {}
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val text = searchField.text.toString()
                subject?.onNext(text)
            }
        })
    }

    private fun configureRecycleView() {
        searchCitiesView.layoutManager = LinearLayoutManager(this)
        val str = dataProvider.data().asSequence().map { it.name.toString() }.toCollection(ArrayList())
        adapter = MainAdapter(str, this)
        adapter?.clickedOnIndex = {
            Log.d("CLICK", "Clicked at: $it")
            val element = dataProvider.get(it)
            storage.addIfCan("savedCities", element)
            this.finish()
        }

        searchCitiesView.adapter = adapter
    }
}

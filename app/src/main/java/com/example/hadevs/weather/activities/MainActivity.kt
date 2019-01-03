package com.example.hadevs.weather.activities

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import com.example.hadevs.weather.R
import com.example.hadevs.weather.adapters.MainAdapter
import com.example.hadevs.weather.data_providers.MainDataProvider
import com.example.hadevs.weather.interactors.StorageInteractor
import io.paperdb.Paper

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity() {

    private val dataProvider: MainDataProvider = MainDataProvider()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Paper.init(this)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        fab.setOnClickListener {
            val intent = Intent(this, SearchPlaceActivity::class.java)
            startActivityForResult(intent, 0)
        }

        overridePendingTransition(
            R.anim.slide_in,
            R.anim.slide_out
        ) // slide animation

        configureRecycleView()
    }

    override fun onStart() {
        super.onStart()
        dataProvider.updateData()
        configureRecycleView()
    }

    private fun configureRecycleView() {

        searchCitiesView.layoutManager = LinearLayoutManager(this)
        searchCitiesView.adapter = MainAdapter(dataProvider.data(), this)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}

package com.example.hadevs.weather

import android.support.v7.app.AppCompatActivity
import android.os.Bundle

class SearchPlaceActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_place)
        val actionBar = supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(true)
        overridePendingTransition(R.anim.slide_in, R.anim.slide_out) // slide animation
    }
}

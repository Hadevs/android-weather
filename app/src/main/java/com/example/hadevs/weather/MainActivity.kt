package com.example.hadevs.weather

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity;
import android.text.Editable
import android.text.TextWatcher
import android.view.Menu
import android.view.MenuItem
import com.example.hadevs.weather.managers.ApixuManager
import com.example.hadevs.weather.models.ApixuCurrentResponse
import io.reactivex.subjects.PublishSubject

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        startObservingCityField()
    }

    private var subject: PublishSubject<String>? = null
    @SuppressLint("CheckResult")
    private fun startObservingCityField() {
        subject = PublishSubject.create()
        subject?.debounce(100, TimeUnit.MILLISECONDS)?.subscribe() {
            ApixuManager.loadCurrentModel(it) { model ->
                updateText(model)
            }
        }
        cityTextField.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {}
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val text = cityTextField.text.toString()
                subject?.onNext(text)
            }

        })
    }

    @SuppressLint("SetTextI18n")
    fun updateText(responseModel: ApixuCurrentResponse) {
        val temperature = responseModel.current?.temp_c.toString()
        textView.text = "$temperature°C"
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

package com.example.hadevs.weather.adapters
import android.annotation.SuppressLint
import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.hadevs.weather.R
import com.example.hadevs.weather.help_classes.ItemClosure

class MainAdapter(val items : ArrayList<String>, val context: Context) : RecyclerView.Adapter<ViewHolder>() {
    @SuppressLint("ResourceType")
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.xml.city_item, p0, false))
    }

    var clickedOnIndex: ItemClosure<Int>? = null

    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {
        p0.textView.text = items[p1]
        p0.itemView.setOnClickListener {
            clickedOnIndex?.invoke(p1)
        }
    }

    // Gets the number of animals in the list
    override fun getItemCount(): Int {
        return items.size
    }

}

class ViewHolder (view: View) : RecyclerView.ViewHolder(view) {
    // Holds the TextView that will add each animal to
    val textView = view.findViewById<TextView>(R.id.textView)!!
}
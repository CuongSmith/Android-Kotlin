package com.example.kotlin_code_by_csplus

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class LocationAdapter(val context: Context)
    : RecyclerView.Adapter<LocationAdapter.ViewHolder>(){

    var list = ArrayList<Location>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(context)
        val locationView = inflater.inflate(R.layout.activity_view, parent, false)
        return ViewHolder(locationView)
    }

    fun loadView(list: List<Location>) {
        this.list.addAll(list)
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val location = list.get(position)
        holder.apply {
            textViewName.text = location.name
            textViewLagitude.text = location.lagitude
            textViewLongitude.text = location.longitude
        }
        Glide.with(holder.imageLocation).load(location.link).into(holder.imageLocation)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    inner class ViewHolder (listItemView: View) : RecyclerView.ViewHolder(listItemView) {
        val imageLocation = itemView.findViewById<ImageView>(R.id.image_location)
        val textViewName = itemView.findViewById<TextView>(R.id.textView_Name)
        val textViewLagitude = itemView.findViewById<TextView>(R.id.textView_Lagitude)
        val textViewLongitude = itemView.findViewById<TextView>(R.id.textView_Longitude)
    }

}
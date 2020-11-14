package com.example.maps

import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.maps.DTB.AppDatabase
import com.example.maps.Table.Links
import com.example.maps.Table.LocationEntity
import com.example.maps.flickr.FlickrCall

class SecondActivity : AppCompatActivity(){

    val locationAdapter by lazy { LocationAdapter(this) }
    val list by lazy { ArrayList<Location>() }
    lateinit var name: String
    lateinit var lagitude: String
    lateinit var longitude: String
    lateinit var rvLocation: RecyclerView
    var id: Int = 0
//    lateinit var swipeRefreshLayout : SwipeRefreshLayout
//    lateinit var respone: Respone

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        name = intent.getStringExtra(MainActivity.EXTRA_TEXTNAME)!!
        lagitude = intent.getStringExtra(MainActivity.EXTRA_TEXTLAGITUDE)!!
        longitude = intent.getStringExtra(MainActivity.EXTRA_TEXTLONGITUDE)!!

        rvLocation = findViewById<RecyclerView>(R.id.image_location)!!
        rvLocation.adapter = locationAdapter
        rvLocation.layoutManager = LinearLayoutManager(parent)

        FlickrCall.callApi(lagitude, longitude) {loadRecycleView(it)}
    }

    fun loadRecycleView(res: Respone) {
        id = AppDatabase.newInstance(applicationContext).itemDao()
            .getID(lat = lagitude.toDouble(), lon = longitude.toDouble())

        for (i in 0..res.photos.photo.size-1) {
            list.add(Location(name, lagitude, longitude, res.photos.photo[i].getLink()))
            AppDatabase.newInstance(applicationContext).linkDao()
                .insertLinks(Links(id = id ,link = res.photos.photo[i].getLink()))
        }
        locationAdapter.loadView(list)
    }
}
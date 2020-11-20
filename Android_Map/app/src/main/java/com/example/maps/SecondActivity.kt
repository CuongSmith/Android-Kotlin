package com.example.maps

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.maps.DTB.AppDatabase
import com.example.maps.Table.Links
import com.example.maps.flickr.FlickrCall

class SecondActivity : AppCompatActivity() {

    val locationAdapter by lazy { LocationAdapter(this) }
    val list by lazy { ArrayList<Location>() }
    lateinit var name: String
    lateinit var lagitude: String
    lateinit var longitude: String
    lateinit var rvLocation: RecyclerView
    var id: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        name = intent.getStringExtra(MainActivity.EXTRA_TEXTNAME)!!
        lagitude = intent.getStringExtra(MainActivity.EXTRA_TEXTLAGITUDE)!!
        longitude = intent.getStringExtra(MainActivity.EXTRA_TEXTLONGITUDE)!!

        rvLocation = findViewById<RecyclerView>(R.id.image_location)!!
        rvLocation.adapter = locationAdapter
        rvLocation.layoutManager = LinearLayoutManager(parent)

        id = AppDatabase.newInstance(applicationContext).itemDao()
            .getID(lat = lagitude.toDouble(), lon = longitude.toDouble())

        val linK: List<String> = AppDatabase
            .newInstance(applicationContext)
            .linkDao().getLinkid(id)

        if (linK.isEmpty()) {
            FlickrCall.callApi(lagitude, longitude) {
                if (it.photos.photo.isEmpty()) {
                    setResult(RESULT_OK)
                    finish()
                }
                loadRecycleView(it)
            }
        } else {
            for (i in 0..linK.size - 1) {
                list.add(Location(name, lagitude, longitude, linK[i]))
            }
            locationAdapter.loadView(list)
        }
    }

    fun loadRecycleView(res: Respone) {
        for (i in 0..res.photos.photo.size - 1) {
            list.add(Location(name, lagitude, longitude, res.photos.photo[i].getLink()))
            AppDatabase.newInstance(applicationContext).linkDao()
                .insertLinks(Links(id = id, link = res.photos.photo[i].getLink()))
        }
        locationAdapter.loadView(list)
    }
}
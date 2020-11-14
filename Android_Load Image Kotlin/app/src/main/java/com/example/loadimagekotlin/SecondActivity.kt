package com.example.loadimagekotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.loadimagekotlin.flickr.FlickrCall

class SecondActivity : AppCompatActivity(), View.OnClickListener {
    var count = 0

    val locationAdapter by lazy { LocationAdapter(this) }
    val list by lazy { ArrayList<Location>() }
    lateinit var name: String
    lateinit var lagitude: String
    lateinit var longitude: String
    lateinit var rvLocation: RecyclerView
    lateinit var swipeRefreshLayout : SwipeRefreshLayout
    lateinit var addImage : Button

    lateinit var response : Respone

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        name = intent.getStringExtra(MainActivity.EXTRA_TEXTNAME)!!
        lagitude = intent.getStringExtra(MainActivity.EXTRA_TEXTLAGITUDE)!!
        longitude = intent.getStringExtra(MainActivity.EXTRA_TEXTLONGITUDE)!!

        rvLocation = findViewById<RecyclerView>(R.id.rvLocation)!!
        rvLocation.adapter = locationAdapter
        rvLocation.layoutManager = LinearLayoutManager(parent)

        FlickrCall.callApi(lagitude, longitude) { loadRecycleView(it) }

        swipeRefreshLayout = findViewById(R.id.swiperefresh)
        swipeRefreshLayout.setOnRefreshListener {
//            Log.d("AAA", "aduma")
            FlickrCall.callApi(lagitude, longitude) {
                response = it
                resetRecycleView(response)
            }
            swipeRefreshLayout.isRefreshing = false
        }

        addImage = findViewById(R.id.addimage)
        addImage.setOnClickListener (this)
    }

    fun resetRecycleView(res: Respone) {
        count = 0
        list.clear()
        for (i in count..count+9) {
            list.add(Location(name, lagitude, longitude, res.photos.photo[i].getLink()))
//            Log.d("BBB", i.toString())
        }
        locationAdapter.resetView(list)
    }

    fun loadRecycleView(res: Respone) {
        for (i in count..count+9) {
            list.add(Location(name, lagitude, longitude, res.photos.photo[i].getLink()))
        }
        locationAdapter.addView(list)
    }

    fun addImage() {
        count += 10
        FlickrCall.callApi(lagitude, longitude) {
            list.clear()
            loadRecycleView(it)
        }
    }

    override fun onClick(v: View?) {
        addImage()
    }
}
package com.example.kotlin_code_by_csplus

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class SecondActivity : AppCompatActivity() {
    val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(MainActivity.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    val locationAdapter by lazy { LocationAdapter(this) }
    val list by lazy { ArrayList<Location>() }
    val respone by lazy { retrofit.create(FlickrAPIService::class.java) }
    lateinit var name: String
    lateinit var lagitude: String
    lateinit var longitude: String
    lateinit var rvLocation:RecyclerView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        name = intent.getStringExtra(MainActivity.EXTRA_TEXTNAME)!!
        lagitude = intent.getStringExtra(MainActivity.EXTRA_TEXTLAGITUDE)!!
        longitude = intent.getStringExtra(MainActivity.EXTRA_TEXTLONGITUDE)!!
        rvLocation = findViewById<RecyclerView>(R.id.rvLocation)!!
        rvLocation.adapter = locationAdapter
        rvLocation.layoutManager = LinearLayoutManager(parent)
        callApi { loadRecycleView(it) }
    }

    fun callApi(call :(res: Respone) -> Unit) {
        val call = respone.getPhotos(
            MainActivity.METHOD,
            MainActivity.API_KEY,
            lagitude!!,
            longitude!!,
            "json",
            "1"
        ).enqueue(object : Callback<Respone> {
            override fun onResponse(call: Call<Respone>, response: Response<Respone>) {
                val res = response.body() as Respone
                call(res)
            }

            override fun onFailure(call: Call<Respone>, t: Throwable) {
                Log.d("onFailure", t.toString())
            }
        })
    }

    fun loadRecycleView(res: Respone) {
        for (i in 0..10)
            list.add(Location(name, lagitude, longitude, res.photos.photo[i].getLink()))
        locationAdapter.loadView(list)
    }
}
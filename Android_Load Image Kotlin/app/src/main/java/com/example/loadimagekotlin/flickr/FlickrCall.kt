package com.example.loadimagekotlin.flickr

import android.util.Log
import com.example.loadimagekotlin.Respone
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object FlickrCall {
    val BASE_URL = "https://www.flickr.com/"
    val API_KEY = "f5ef9f49b4370decbb262cfe9cc85bdc"
    val METHOD = "flickr.photos.search"
    val retrofit =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(FlickApiService::class.java)

    fun callApi(lagitude: String, longitude: String, call: (res: Respone) -> Unit) {
        retrofit.getPhotos(
            METHOD,
            API_KEY,
            lagitude,
            longitude,
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
}
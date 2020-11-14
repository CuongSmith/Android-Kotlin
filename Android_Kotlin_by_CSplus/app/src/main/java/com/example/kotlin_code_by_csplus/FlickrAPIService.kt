package com.example.kotlin_code_by_csplus

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface FlickrAPIService {
    @GET("service/rest/")
    fun getPhotos(
        @Query("method") method: String,
        @Query("api_key") key: String,
        @Query("lat") lat: String,
        @Query("lon") lon: String,
        @Query("format") format: String,
        @Query("nojsoncalback") calback: String
    ) : Call<Respone>
}
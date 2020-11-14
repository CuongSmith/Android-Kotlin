package com.example.kotlin_code_by_csplus

data class Respone (
    val photos: Photos,
    val sta: String
){
    fun getResult(num: Int) = photos.getLinks(num)
}
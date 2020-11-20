package com.example.maps

class Location() {
    var name: String? = null
    var lagitude: String? = null
    var longitude: String? = null
    var link: String? = null

    constructor(name: String?, lagitude: String?, longitude: String?, link: String?) : this() {
        this.name = if (name != null) name else ""
        this.lagitude = if (lagitude != null) lagitude else ""
        this.longitude = if (longitude != null) longitude else ""
        this.link = if (link != null) link else ""
    }

//    init {
//        name = ""
//        lagitude = ""
//        longitude = ""
//        link = ""
//    }
}
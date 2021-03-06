package com.example.kotlin_code_by_csplus

class Location() {
    var name: String
    var lagitude: String
    var longitude: String
    var link: String

    constructor(name: String?, lagitude: String?, longitude: String?, link: String?) : this() {
        this.name = if (name != null) name else ""
        this.lagitude = if (lagitude != null) lagitude else ""
        this.longitude = if (longitude != null) longitude else ""
        this.link = if (link != null) link else ""
    }

    init {
        name = ""
        lagitude = ""
        longitude = ""
        link = ""
    }
}
package com.example.debug

class Calculator(mainActivity: MainActivity) {

    var first: Double = 0.0
    var second: Double = 0.0

    fun add() : Double {
        return (first+second)
    }

    fun sub() : Double {
        return (first-second)
    }

    fun mul() : Double {
        return (first*second)
    }

    fun div() : Double {
        return (first/second)
    }

}
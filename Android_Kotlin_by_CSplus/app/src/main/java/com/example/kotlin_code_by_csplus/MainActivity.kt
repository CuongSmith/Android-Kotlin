package com.example.kotlin_code_by_csplus

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText

class MainActivity : AppCompatActivity() {

    companion object {
        val EXTRA_TEXTNAME : String = MainActivity::class.java.name + ".extra.TEXTNAME"
        val EXTRA_TEXTLAGITUDE : String = MainActivity::class.java.name + ".extra.TEXTLAGITUDE"
        val EXTRA_TEXTLONGITUDE : String = MainActivity::class.java.name + ".extra.TEXTLONGITUDE"

        val BASE_URL = "https://www.flickr.com/"
        val API_KEY = "f5ef9f49b4370decbb262cfe9cc85bdc"
        val METHOD = "flickr.photos.search"
    }

    lateinit var nameText : EditText
    lateinit var lagitudeText : EditText
    lateinit var longitudeText : EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        nameText = findViewById(R.id.text_name)
        lagitudeText = findViewById(R.id.text_lagitude)
        longitudeText = findViewById(R.id.text_longitude)
    }

    fun loadImage(view: View) {
        val intent = Intent(this, SecondActivity::class.java)
        intent.apply {
            putExtra(EXTRA_TEXTNAME, nameText.text.toString())
            putExtra(EXTRA_TEXTLAGITUDE, lagitudeText.text.toString())
            putExtra(EXTRA_TEXTLONGITUDE, longitudeText.text.toString())
        }
        startActivity(intent)
    }

}


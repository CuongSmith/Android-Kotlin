package com.example.debug

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    lateinit var buttonadd : Button
    lateinit var buttonsub : Button
    lateinit var buttonmul : Button
    lateinit var buttondiv : Button

    lateinit var result : TextView
    lateinit var sothu1 : EditText
    lateinit var sothu2 : EditText

    var sothunhat : Double = 0.0
    var sothuhai : Double = 0.0
    var ketqua : Double = 0.0

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        sothu1 = findViewById(R.id.first)
        sothu2 = findViewById(R.id.second)
        result = findViewById(R.id.result)
        buttonadd = findViewById(R.id.add)
        buttonsub = findViewById(R.id.sub)
        buttonmul = findViewById(R.id.mul)
        buttondiv = findViewById(R.id.div)

        buttonadd.setOnClickListener {
            sothunhat = (sothu1.text.toString()).toDouble()
            sothuhai = (sothu2.text.toString()).toDouble()
            ketqua = ((sothunhat + sothuhai).toDouble())
            result.text = ketqua.toString()
        }

        buttonsub.setOnClickListener {
            sothunhat = (sothu1.text.toString()).toDouble()
            sothuhai = (sothu2.text.toString()).toDouble()
            ketqua = (sothunhat - sothuhai).toDouble()
            result.text = ketqua.toString()
        }

        buttonmul.setOnClickListener {
            sothunhat = (sothu1.text.toString()).toDouble()
            sothuhai = (sothu2.text.toString()).toDouble()
            ketqua = (sothunhat * sothuhai).toDouble()
            result.text = ketqua.toString()
        }

        buttondiv.setOnClickListener {
            sothunhat = (sothu1.text.toString()).toDouble()
            sothuhai = (sothu2.text.toString()).toDouble()
            if(sothuhai == 0.0) {
                result.text = "error"
            } else {
                ketqua = (sothunhat / sothuhai).toDouble()
                result.text = ketqua.toString()
            }
        }
    }

}
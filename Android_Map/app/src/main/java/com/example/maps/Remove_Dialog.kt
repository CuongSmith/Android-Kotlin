package com.example.maps

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.widget.Button

class Remove_Dialog(context: Context) : Dialog(context) {

    lateinit var button_Yes: Button
    lateinit var button_No: Button

    interface RemoveDialogListener {
        fun onYesClick()
    }

    private lateinit var listener: RemoveDialogListener
    fun setListener(listener: RemoveDialogListener) {
        this.listener = listener
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dialog)
        button_Yes = findViewById(R.id.yes)
        button_No = findViewById(R.id.no)

        button_No.setOnClickListener {
            dismiss()
        }

        button_Yes.setOnClickListener {
            listener.onYesClick()
        }
    }
}
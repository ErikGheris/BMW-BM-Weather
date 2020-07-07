package com.example.bmweather

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        search_button.setOnClickListener {
            Toast.makeText(this, "this is a toast message",
                Toast.LENGTH_SHORT).show()
        }

        // clears the autoCompleteTExtView when it is clicked
        autoCompleteTextView.setOnClickListener {
            autoCompleteTextView.setText("")
        }

    }

}
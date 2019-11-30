package com.example.dagallery

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class sketchActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sketch)
        supportActionBar?.title="Sketch"
    }

}

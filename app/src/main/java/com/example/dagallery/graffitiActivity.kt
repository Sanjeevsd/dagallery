package com.example.dagallery

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class graffitiActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_graffiti)
        supportActionBar?.title="Graffiti"
    }
}

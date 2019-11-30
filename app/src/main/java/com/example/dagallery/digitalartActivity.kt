package com.example.dagallery

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class digitalartActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_digitalart)
        supportActionBar?.title="Digital Art"
    }
}

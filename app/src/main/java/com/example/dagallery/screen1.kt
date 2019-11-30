package com.example.dagallery

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_screen1.*

class screen1 : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_screen1)
        val authCheck= FirebaseAuth.getInstance()
        if (authCheck.getCurrentUser() != null){
            val hintent=Intent(this,MainActivity::class.java)
            startActivity(hintent)
            finish()
        }
        image1.setOnClickListener {
            val intent=Intent(this,screen2::class.java)
            startActivity(intent)
        }
    }

}

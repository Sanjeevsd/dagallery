package com.example.dagallery

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login.*

class loginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val authCheck=FirebaseAuth.getInstance()
        if (authCheck.getCurrentUser() != null){
            val hintent=Intent(this,MainActivity::class.java)
            startActivity(hintent)
            finish()
        }
            register_here_view.setOnClickListener {
               val intent= Intent(this,registerActivity::class.java)
                startActivity(intent)
                finish()
            }
        login_button.setOnClickListener {
            performlogin()
        }
    }
    private fun performlogin(){
        val login_email=email_login.text.toString()
        val password_login=login_password.text.toString()
        FirebaseAuth.getInstance().signInWithEmailAndPassword(login_email,password_login)
            .addOnSuccessListener {
                Toast.makeText(this,"Successfully LoggedIn", Toast.LENGTH_LONG).show()
                val intent=Intent(this,MainActivity::class.java)
                startActivity(intent)
                finish()
            }
    }
}

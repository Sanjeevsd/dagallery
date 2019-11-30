package com.example.dagallery

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_register.*

class registerActivity : AppCompatActivity() {
    var role_selected:String=""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        supportActionBar?.title="Registration"
        login_here_view.setOnClickListener {
            val intent=Intent(this,loginActivity::class.java)
            startActivity(intent)
            finish()
        }
        val options=arrayOf("Select Role","Seller","General User")
        spinner_register_role.adapter= ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,options)
        spinner_register_role.onItemSelectedListener= object : AdapterView.OnItemClickListener,
            AdapterView.OnItemSelectedListener {
            override fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                role_selected=""
            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                role_selected=options.get(position)
                Log.d("note","$role_selected is selected")
            }
        }
       button_register.setOnClickListener {
           perform_register(role_selected)
       }

    }
    private fun perform_register(roleSelect:String){
        val email=email_register.text.toString()
        val password=password_register.text.toString()
        val fullname=name_register.text.toString()

        if(email.isNotEmpty() && password.isNotEmpty() && fullname.isNotEmpty()&& roleSelect.isNotEmpty()){
            FirebaseAuth.getInstance().createUserWithEmailAndPassword(email,password)
                .addOnSuccessListener {
                   val  uid=FirebaseAuth.getInstance().uid.toString()
                    val ref=FirebaseDatabase.getInstance().getReference("/users/$uid")
                    val datauser=usremodal(fullname,email,uid,roleSelect)
                    ref.setValue(datauser)
                        .addOnSuccessListener {
                            Toast.makeText(this,"Successfully Registered",Toast.LENGTH_LONG).show()
                            val intent=Intent(this,MainActivity::class.java)
                            startActivity(intent)
                            finish()
                        }


                }
                .addOnFailureListener {
                    Toast.makeText(this,"Failed to Registered",Toast.LENGTH_LONG).show()
                }
        }
    }
}

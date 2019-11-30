package com.example.dagallery

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.activity_register.*

class registerActivity : AppCompatActivity() {
    var role_selected:String=""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        supportActionBar?.title="Paintings"
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
    }
}

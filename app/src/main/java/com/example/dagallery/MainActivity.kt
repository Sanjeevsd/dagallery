package com.example.dagallery

import android.content.Intent
import android.os.Bundle
import android.util.Log
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.nav_header_main.*

class MainActivity : AppCompatActivity() {
var count=0
    val uid=FirebaseAuth.getInstance().uid.toString()
    val ref=FirebaseDatabase.getInstance().getReference("/users/$uid")
    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

           if (count==0){

               ref.keepSynced(true)
               ref.addListenerForSingleValueEvent(object : ValueEventListener {
                   override fun onCancelled(p0: DatabaseError) {
                   }
                   override fun onDataChange(Uname: DataSnapshot) {
                       val nameofuser= Uname.getValue(usremodal::class.java)
                       Log.d("hactivity","special case:${nameofuser?.uname.toString()} and :${nameofuser?.uid.toString()}")
                       val nameofcurrentuser=nameofuser?.uname.toString()
                       val emailcurrent=nameofuser?.uemail.toString()
                       current_email?.text=emailcurrent
                       currentuser_name?.text=nameofcurrentuser

                   }
               }
               )
               count=count+1
           }
        val fab: FloatingActionButton = findViewById(R.id.fab)
        fab.setOnClickListener {
            val intent=Intent(this,uploadActivity::class.java)
            startActivity(intent)
        }
        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        val navView: NavigationView = findViewById(R.id.nav_view)
        val navController = findNavController(R.id.nav_host_fragment)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_home, R.id.nav_slideshow,
                R.id.nav_tools, R.id.nav_send
            ), drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks (logout)

        when (item.itemId) {

            R.id.action_settings->{
                FirebaseAuth.getInstance().signOut()
                Toast.makeText(this,"Successfully LoggedOut",Toast.LENGTH_SHORT).show()
                val intent=Intent(this,loginActivity::class.java)
                startActivity(intent)
                finish()
            }
            else -> return super.onOptionsItemSelected(item)
        }
        return true
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

}

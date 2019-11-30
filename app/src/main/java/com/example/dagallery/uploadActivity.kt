package com.example.dagallery

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.firebase.storage.FirebaseStorage
import kotlinx.android.synthetic.main.activity_upload.*
import java.util.*

class uploadActivity : AppCompatActivity() {
    var imageurl:String=""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_upload)
        upload_picture.setOnClickListener {
            uploadpicure()
        }
        select_picture.setOnClickListener {
            selectpicture()
        }
    }
    private fun uploadpicure(){
        val price=price_picture.text.toString()

    }
    private  fun selectpicture(){
        val selectPic = Intent(Intent.ACTION_PICK)
        selectPic.type = "image/*"
        startActivityForResult(selectPic, 0)
    }
    @SuppressLint("MissingSuperCall")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == 0 && resultCode == Activity.RESULT_OK && data != null) {
            Log.d("admin", "photowas selected")
            var selectedPhotouri = data.data
            if (selectedPhotouri != null) {
                val fileid = UUID.randomUUID().toString()
                val imageref = FirebaseStorage.getInstance().getReference("/images/$fileid")
                imageref.putFile(selectedPhotouri)
                    .addOnSuccessListener {
                        Log.d("admin", "Uploaded image")
                        imageref.downloadUrl.addOnSuccessListener {

                            Log.d("admin", "urlis${it.toString()}")
                            imageurl = it.toString()
                        }
                    }
            }
        }

    }
}

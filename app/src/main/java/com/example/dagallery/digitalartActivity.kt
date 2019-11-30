package com.example.dagallery

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.squareup.picasso.Picasso
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.Item
import kotlinx.android.synthetic.main.activity_digitalart.*
import kotlinx.android.synthetic.main.imageview.view.*

class digitalartActivity : AppCompatActivity() {
        val ref=FirebaseDatabase.getInstance().getReference()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_digitalart)
        supportActionBar?.title="Digital Art"
        val noticemanager= androidx.recyclerview.widget.LinearLayoutManager(this)
        noticemanager.reverseLayout=true
        noticemanager.stackFromEnd=true
        painting_recycler.layoutManager=noticemanager//can use this statement in xml also
        fetchdatas()
    }
    private fun fetchdatas(){
        val auth= FirebaseAuth.getInstance().uid
        val choosesection=ref.child("user/$auth/section")
                val Nref=ref.child("/dataimage")
                Nref.addListenerForSingleValueEvent(object : ValueEventListener {
                    override fun onCancelled(p0: DatabaseError) {
                    }
                    override fun onDataChange(p0: DataSnapshot) {
                        val adapter= GroupAdapter<GroupieViewHolder>()
                        p0.children.forEach {
                            val noticet=it.getValue(datammodel::class.java)
                            Log.d("notice","time is:${noticet?.price}")
                            if (noticet!=null)
                            {
                                adapter.add(dataitem(noticet))
                            }
                        }
                        painting_recycler.adapter=adapter
                    }
                })  }


    }

class dataitem(val noticetitle: datammodel): Item<GroupieViewHolder>(){
    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
        viewHolder.itemView.price_product.text=noticetitle.price
        val refer=FirebaseDatabase.getInstance().getReference("users/${noticetitle.uid}")
        refer.addListenerForSingleValueEvent(object :ValueEventListener{
            override fun onCancelled(p0: DatabaseError) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onDataChange(p0: DataSnapshot) {
              val name=  p0.child("uname").getValue().toString()
                Log.d("datas","$name")
                viewHolder.itemView.name_product.text=name
            }

        })

        Log.d("notice",noticetitle.url)
         viewHolder.itemView.tanjito_view.setImageDrawable(null)
        if (noticetitle.url!=""){
            Log.d("datas",noticetitle.url)
            Picasso.get().load(noticetitle.url).fit().into(viewHolder.itemView.tanjito_view)

        }
        else Log.d("sffgh","failed")
    }
    override fun getLayout(): Int {
        return R.layout.imageview
    }
}

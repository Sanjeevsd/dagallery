package com.example.dagallery.ui.gallery

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.example.dagallery.*

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
            ViewModelProviders.of(this).get(HomeViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_home, container, false)
//        val textView: TextView = root.findViewById(R.id.text_home)
//        homeViewModel.text.observe(this, Observer {
//            textView.text = it
//        })
        val digital_cards=root.findViewById(R.id.digitalart_card) as CardView
        val sketch_cards=root.findViewById(R.id.sketch_card) as CardView
        val paintings_cards=root.findViewById(R.id.paintings_card) as CardView
        val graffiti_cards=root.findViewById(R.id.graffiti_card) as CardView
        digital_cards.setOnClickListener {

            val intent= Intent(context, digitalartActivity::class.java)
            startActivity(intent)
        }
        sketch_cards.setOnClickListener {

            val intent= Intent(context, sketchActivity::class.java)
            startActivity(intent)
        }
        paintings_cards.setOnClickListener {

            val intent= Intent(context, paintingActivity::class.java)
            startActivity(intent)
        }
        graffiti_cards.setOnClickListener {

            val intent= Intent(context, graffitiActivity::class.java)
            startActivity(intent)
        }
        return root
    }
}
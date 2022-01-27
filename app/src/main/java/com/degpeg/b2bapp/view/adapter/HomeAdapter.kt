package com.degpeg.b2bapp.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnLongClickListener
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.degpeg.b2bapp.view.activity.HomeFragment
import com.example.b2bapp.R

class HomeAdapter(private val context: Context?, private var homeFragment: HomeFragment) :
    RecyclerView.Adapter<HomeAdapter.MyViewHolder?>() {

    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view),
        OnLongClickListener, View.OnClickListener {

        override fun onClick(view: View) {

        }

        init {
            view.setOnClickListener(this)
        }

        override fun onLongClick(p0: View?): Boolean {
            TODO("Not yet implemented")
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.activity_home_items, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

    }

    override fun getItemCount(): Int {
        return 3
    }
}
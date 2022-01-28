package com.degpeg.b2bapp.view.adapter

import android.content.Context
import android.net.Uri
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.ImageLoader
import coil.decode.SvgDecoder
import coil.load
import com.degpeg.b2bapp.model.response.ChannelsResponse
import com.squareup.picasso.Picasso
import com.example.b2bapp.R


class ChannelsAdapter(private val channelsResponse: ArrayList<ChannelsResponse?>?, val context: Context?) :
    RecyclerView.Adapter<ChannelsAdapter.MyViewHolder?>() {

    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view),
        View.OnLongClickListener, View.OnClickListener {

          var channelImage:ImageView?=null
          var channelTitle:TextView?=null

        override fun onClick(view: View) {

        }

        init {
            channelImage = view.findViewById(R.id.channel_image)
            channelTitle = view.findViewById(R.id.channel_title)
            view.setOnClickListener(this)
        }

        override fun onLongClick(p0: View?): Boolean {
            TODO("Not yet implemented")
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.selected_channels_items, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val channelsListResponse = channelsResponse?.get(position)
        Log.e("name",channelsListResponse?.logo.toString())
        Picasso.get().isLoggingEnabled = true
        val image:String = channelsListResponse?.logo.toString()
        Picasso.get().load(image).placeholder(R.drawable.facebook)
                .into(holder.channelImage)
        holder.channelTitle?.text = channelsListResponse?.name

    }

    override fun getItemCount(): Int {
        return channelsResponse?.size!!
    }










}
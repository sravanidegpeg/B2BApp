package com.degpeg.b2bapp.view.activity

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.degpeg.b2bapp.model.response.ChannelsResponse
import com.degpeg.b2bapp.presenter.implemention.ChannelsPresenter
import com.degpeg.b2bapp.presenter.interfaces.ChannelsMainView
import com.degpeg.b2bapp.view.adapter.ChannelsAdapter
import com.degpeg.b2bapp.view.adapter.HomeAdapter
import com.degpeg.b2bapp.view.adapter.UpCommingSessionAdapter
import com.example.b2bapp.R

class HomeFragment : Fragment(),ChannelsMainView {

    fun newInstance(someInt: Int): HomeFragment? {
        val myFragment = HomeFragment()
        val args = Bundle()
        args.putInt("someInt", someInt)
        myFragment.arguments = args
        return myFragment
    }

    var channelsPresenter = ChannelsPresenter(this)
    private var channelslist:RecyclerView?=null


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view  = inflater.inflate(R.layout.activity_home,container,false)
        val bannerslist = view.findViewById<RecyclerView>(R.id.banner_recyclerview)
        val upcommingsessionlist = view.findViewById<RecyclerView>(R.id.upcomming_session_recyclerview)
        channelslist = view.findViewById(R.id.channels_recyclerview)
        val bannersadapter = HomeAdapter( context, this)
        val upCommingSessionAdapter = UpCommingSessionAdapter(context,this)

        //Banners adapter
        bannerslist.layoutManager = LinearLayoutManager(
            context,
            LinearLayoutManager.HORIZONTAL,
            true
        )
        (bannerslist.layoutManager as LinearLayoutManager).findFirstVisibleItemPosition()
        bannerslist.addItemDecoration(LinePagerIndicatorDecoration())
        bannerslist.adapter = bannersadapter

        //Upcommingsession adapter
        upcommingsessionlist.layoutManager = LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,true)
        upcommingsessionlist.adapter = upCommingSessionAdapter

        channelsPresenter.getChannels()



        return view
    }

    override fun getChannels(channelsResponse: ArrayList<ChannelsResponse?>?) {
        //channels adapter
        channelslist?.layoutManager = LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,true)
        Log.e("channels","channel is coming or not")
        val  channelsAdapter = ChannelsAdapter(channelsResponse,context)
        channelslist?.adapter = channelsAdapter
    }

    override fun failure(msg: String?, errorStatus: Boolean) {
        Log.e("faliure response", "" + errorStatus)
    }
}
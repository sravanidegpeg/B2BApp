package com.degpeg.b2bapp.presenter.implemention

import android.util.Log
import com.degpeg.b2bapp.helpers.ApiClient
import com.degpeg.b2bapp.model.response.ChannelsResponse
import com.degpeg.b2bapp.network.ApiInterface
import com.degpeg.b2bapp.presenter.interfaces.ChannelsMainView
import com.degpeg.b2bapp.presenter.interfaces.IChannels
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ChannelsPresenter(var channelsMainView: ChannelsMainView) : IChannels {
    var apiInterface: ApiInterface = ApiClient().getClient()!!.create(ApiInterface::class.java)
    override fun getChannels() {
        val call: Call<ArrayList<ChannelsResponse?>?>? = apiInterface.getChannels()
        call?.enqueue(object : Callback<ArrayList<ChannelsResponse?>?> {
            override fun onResponse(
                call: Call<ArrayList<ChannelsResponse?>?>,
                response: Response<ArrayList<ChannelsResponse?>?>
            ) {
                if (response.isSuccessful) {
                    channelsMainView.getChannels(response.body())
                    Log.e("TAG", "channels response: " + Gson().toJson(response.body()))
                }
            }

            override fun onFailure(call: Call<ArrayList<ChannelsResponse?>?>, t: Throwable) {
                channelsMainView.failure(t.message, false)
            }

        })
    }


}
package com.degpeg.b2bapp.presenter.interfaces

import com.degpeg.b2bapp.model.response.ChannelsResponse

interface ChannelsMainView {
    fun getChannels(channelsResponse: ArrayList<ChannelsResponse?>?)
    fun failure(msg: String?, errorStatus: Boolean)
}
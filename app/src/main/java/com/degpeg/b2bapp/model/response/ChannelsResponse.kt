package com.degpeg.b2bapp.model.response

import com.google.gson.annotations.SerializedName

class ChannelsResponse {
    @SerializedName("name")
    var name: String? = null

    @SerializedName("id")
    var id: String? = null

    @SerializedName("logo")
    var logo: String? = null

    @SerializedName("helpText")
    var helpText: String? = null

    @SerializedName("isEnabled")
    var isEnabledc: Boolean = true
}
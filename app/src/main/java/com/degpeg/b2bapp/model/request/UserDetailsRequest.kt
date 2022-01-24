package com.degpeg.b2bapp.model.request

import com.google.gson.annotations.SerializedName

class UserDetailsRequest {
    @SerializedName("status")
    var status: String? = null
}
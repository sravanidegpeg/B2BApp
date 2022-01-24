package com.degpeg.b2bapp.model.response

import com.google.gson.annotations.SerializedName

class loginResponse {
    @SerializedName("token")
    var token: String? = null
}
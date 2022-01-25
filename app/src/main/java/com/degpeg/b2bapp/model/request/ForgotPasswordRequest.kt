package com.degpeg.b2bapp.model.request

import com.google.gson.annotations.SerializedName

class ForgotPasswordRequest {
    @SerializedName("email")
    var email:String?=null
    @SerializedName("client_api_url")
    var client_api_url:String = ""

}
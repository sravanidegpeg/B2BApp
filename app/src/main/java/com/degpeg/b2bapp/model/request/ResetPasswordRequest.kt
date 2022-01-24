package com.degpeg.b2bapp.model.request

import com.google.gson.annotations.SerializedName

class ResetPasswordRequest {
    @SerializedName("email")
    var email:String?=null
    @SerializedName("client_api_url")
    var client_api_url:String = ""
    @SerializedName("userId")
    var userId:String?=null
    @SerializedName("password")
    var password:String?=null
}
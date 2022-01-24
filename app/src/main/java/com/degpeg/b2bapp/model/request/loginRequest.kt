package com.degpeg.b2bapp.model.request

import com.google.gson.annotations.SerializedName

class loginRequest {
    @SerializedName("password")
    var password:String?=null
    @SerializedName("email")
    var email:String?=null
}
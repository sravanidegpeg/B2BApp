package com.degpeg.b2bapp.model.request

import com.google.gson.annotations.SerializedName

class ResetPasswordRequest {
    @SerializedName("userId")
    var userId:String?=null
    @SerializedName("password")
    var password:String?=null
}
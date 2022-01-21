package com.degpeg.b2bapp.model.request

import com.google.gson.annotations.SerializedName

class signupRequest {
    @SerializedName("email")
    var email: String? = null
    @SerializedName("firstName")
    var firstName: String? = null
    @SerializedName("password")
    var password: String? = null
    @SerializedName("phone")
    var phone:String?=null
    @SerializedName("roles")
    var roles: List<String>? = null
    @SerializedName("status")
    var status: String? = null








}
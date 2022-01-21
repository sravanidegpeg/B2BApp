package com.degpeg.b2bapp.model.response

import com.google.gson.annotations.SerializedName

class signupResponse {
    @SerializedName("roles")
    var roles: List<String>? = null
    @SerializedName("firstName")
    var firstName: String? = null
    @SerializedName("otp")
    var otp: String? = null
    @SerializedName("email")
    var email: String? = null
    @SerializedName("status")
    var status: String? = null
    @SerializedName("id")
    var id: String? = null
}
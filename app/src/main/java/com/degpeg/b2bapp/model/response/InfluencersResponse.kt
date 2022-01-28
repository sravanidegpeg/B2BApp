package com.degpeg.b2bapp.model.response

import com.google.gson.annotations.SerializedName

class InfluencersResponse {
    @SerializedName("id")
    var id:String?=null
    @SerializedName("status")
    var status:String?=null
    @SerializedName("email")
    var email:String?=null
    @SerializedName("name")
    var name:String?=null
    @SerializedName("contact_number")
    var contact_number:String?=null
    @SerializedName("address")
    var address:String?=null
    @SerializedName("addr_city")
    var addr_city:String?=null
    @SerializedName("addr_state")
    var addr_state:String?=null
    @SerializedName("addr_pincode")
    var addr_pincode:String?=null
    @SerializedName("addr_country")
    var addr_country:String?=null
}
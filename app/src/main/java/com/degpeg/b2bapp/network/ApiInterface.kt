package com.degpeg.b2bapp.network

import com.degpeg.b2bapp.model.request.signupRequest
import com.degpeg.b2bapp.model.response.signupResponse
import retrofit2.Call
import retrofit2.http.*

interface ApiInterface {
    @POST("users/signup")
    abstract fun getSignUpDetails(@Body request: signupRequest?): Call<signupResponse?>?
}


package com.degpeg.b2bapp.network

import com.degpeg.b2bapp.model.request.ResetPasswordRequest
import com.degpeg.b2bapp.model.request.UserDetailsRequest
import com.degpeg.b2bapp.model.request.loginRequest
import com.degpeg.b2bapp.model.request.signupRequest
import com.degpeg.b2bapp.model.response.ResetPasswordResponse
import com.degpeg.b2bapp.model.response.loginResponse
import com.degpeg.b2bapp.model.response.signupResponse
import retrofit2.Call
import retrofit2.http.*

interface ApiInterface {
    @POST("users/signup")
    fun getSignUpDetails(@Body request: signupRequest?): Call<signupResponse?>?

    @POST("users/login")
    fun postLogin(@Body request: loginRequest?): Call<loginResponse?>?

    @POST("users/resetpassword")
    fun postResetPasswordEmailVerification(@Body request: ResetPasswordRequest?):Call<ResetPasswordResponse?>?

    @PATCH("update/id")
    fun postResetPassword(@Path("id") userId:String,@Body request: ResetPasswordRequest?):Call<ResetPasswordResponse?>?

    @GET("users/id")
    fun getUserDetails(@Path("id") id:String,@Body userDetailsRequest: UserDetailsRequest)
}


package com.degpeg.b2bapp.network

import com.degpeg.b2bapp.model.request.ForgotPasswordRequest
import com.degpeg.b2bapp.model.request.ResetPasswordRequest
import com.degpeg.b2bapp.model.request.loginRequest
import com.degpeg.b2bapp.model.request.signupRequest
import com.degpeg.b2bapp.model.response.ResetPasswordResponse
import com.degpeg.b2bapp.model.response.UserDetailsResponse
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
    fun postResetPasswordEmailVerification(@Body request: ForgotPasswordRequest?):Call<ResetPasswordResponse?>?

    @PATCH("user-credentials/update/{id}")
    fun postResetPassword(@Path ("id") userid:String,@Body request: ResetPasswordRequest?):Call<ResetPasswordResponse?>?

    @GET("users")
    fun getUsers():Call<ArrayList<UserDetailsResponse?>?>?
}


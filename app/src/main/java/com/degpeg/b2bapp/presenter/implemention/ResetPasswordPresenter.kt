package com.degpeg.b2bapp.presenter.implemention

import android.util.Log
import com.degpeg.b2bapp.helpers.ApiClient
import com.degpeg.b2bapp.model.request.ForgotPasswordRequest
import com.degpeg.b2bapp.model.request.ResetPasswordRequest
import com.degpeg.b2bapp.model.response.ResetPasswordResponse
import com.degpeg.b2bapp.network.ApiInterface
import com.degpeg.b2bapp.presenter.interfaces.IResetPassword
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ResetPasswordPresenter : IResetPassword {
    var apiInterface: ApiInterface = ApiClient().getClient()!!.create(ApiInterface::class.java)

    override fun PostResetPasswordEmailRequest(request: ForgotPasswordRequest?) {
        val call: Call<ResetPasswordResponse?>? = apiInterface.postResetPasswordEmailVerification(request)
        call?.enqueue(object : Callback<ResetPasswordResponse?> {
            override fun onResponse(
                call: Call<ResetPasswordResponse?>,
                response: Response<ResetPasswordResponse?>
            ) {
                if (response.isSuccessful) {
                    Log.e(response.body().toString(),"reset password email verification response:")
                }
            }


            override fun onFailure(call: Call<ResetPasswordResponse?>, t: Throwable) {
                TODO("Not yet implemented")
            }
        })
    }

    override fun PostResetPassword(userid:String,request: ResetPasswordRequest?) {
        val call: Call<ResetPasswordResponse?>? = apiInterface.postResetPassword(userid,request)
        call?.enqueue(object : Callback<ResetPasswordResponse?> {
            override fun onResponse(
                call: Call<ResetPasswordResponse?>,
                response: Response<ResetPasswordResponse?>
            ) {
                if (response.isSuccessful) {
                    Log.e(response.body().toString(),"reset password response:")
                }
            }
            override fun onFailure(call: Call<ResetPasswordResponse?>, t: Throwable) {
            }
        })
    }


}
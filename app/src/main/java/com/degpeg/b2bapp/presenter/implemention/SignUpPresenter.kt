package com.degpeg.b2bapp.presenter.implemention

import android.util.Log
import com.degpeg.b2bapp.helpers.ApiClient
import com.degpeg.b2bapp.model.request.signupRequest
import com.degpeg.b2bapp.model.response.signupResponse
import com.degpeg.b2bapp.network.ApiInterface
import com.degpeg.b2bapp.presenter.interfaces.ISignUp
import com.degpeg.b2bapp.presenter.interfaces.SignupMainView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignUpPresenter(var mainView: SignupMainView) : ISignUp {
    var apiInterface: ApiInterface = ApiClient().getClient()!!.create(ApiInterface::class.java)
    override fun getSignUpDetails(request: signupRequest?) {
        val call: Call<signupResponse?>? = apiInterface.getSignUpDetails(request)
        call?.enqueue(object : Callback<signupResponse?> {
            override fun onResponse(
                call: Call<signupResponse?>,
                response: Response<signupResponse?>) {
                if (response.isSuccessful) {
                    Log.d("signup response", response.message())
                    mainView.getSignUpDetails(response.body())
                }
            }

            override fun onFailure(call: Call<signupResponse?>, t: Throwable) {
                mainView.failure(t.message, false)
            }
        })
    }

}
package com.degpeg.b2bapp.presenter.implemention

import android.util.Log
import com.degpeg.b2bapp.helpers.ApiClient
import com.degpeg.b2bapp.model.request.loginRequest
import com.degpeg.b2bapp.model.response.loginResponse
import com.degpeg.b2bapp.network.ApiInterface
import com.degpeg.b2bapp.presenter.interfaces.ILogin
import com.degpeg.b2bapp.presenter.interfaces.LoginMainView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import com.google.gson.Gson


class LoginPresenter(mainView: LoginMainView) : ILogin {
    var mainView: LoginMainView
    var apiInterface: ApiInterface = ApiClient().getClient()!!.create(ApiInterface::class.java)
    override fun postLogin(request: loginRequest?) {
        val call: Call<loginResponse?>? = apiInterface.postLogin(request)
        call?.enqueue(object : Callback<loginResponse?> {
            override fun onResponse(
                call: Call<loginResponse?>,
                response: Response<loginResponse?>
            ) {
                if (response.isSuccessful) {
                    Log.e("TAG", "response login: " + Gson().toJson(response.body()))
                    mainView.getLoginDetails(response.body())
                }else{
                    response.errorBody()?.string()?.let { mainView.failure("errorbody", it) }
                }
            }

            override fun onFailure(call: Call<loginResponse?>, t: Throwable) {
                TODO("Not yet implemented")
            }
        })
    }

    init {
        this.mainView = mainView
    }
}
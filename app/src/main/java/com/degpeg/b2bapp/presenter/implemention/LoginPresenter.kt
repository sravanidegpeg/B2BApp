package com.degpeg.b2bapp.presenter.implemention

import android.util.Log
import com.degpeg.b2bapp.SessionManager
import com.degpeg.b2bapp.helpers.ApiClient
import com.degpeg.b2bapp.model.request.loginRequest
import com.degpeg.b2bapp.model.response.UserDetailsResponse
import com.degpeg.b2bapp.model.response.loginResponse
import com.degpeg.b2bapp.network.ApiInterface
import com.degpeg.b2bapp.presenter.interfaces.ILogin
import com.degpeg.b2bapp.presenter.interfaces.LoginMainView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import com.google.gson.Gson


class LoginPresenter(var mainView: LoginMainView) : ILogin {
     val session: SessionManager? = null
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

    override fun getUsers() {
        val call: Call<ArrayList<UserDetailsResponse?>?>? = apiInterface.getUsers()
        call?.enqueue(object : Callback<ArrayList<UserDetailsResponse?>?> {
            override fun onResponse(
                call: Call<ArrayList<UserDetailsResponse?>?>,
                response: Response<ArrayList<UserDetailsResponse?>?>
            ) {
                if (response.isSuccessful) {
                    for (i in 0 until response.body()?.size!!){
                        val id = response.body()!![i]?.id
                        mainView.getUsers(id)
                    }
                    Log.e("TAG", "users response: " + Gson().toJson(response.body()))
                }else{
                    response.errorBody()?.string()?.let { mainView.failure("errorbody", it) }
                }
            }

            override fun onFailure(call: Call<ArrayList<UserDetailsResponse?>?>, t: Throwable) {

            }

        })
    }




}
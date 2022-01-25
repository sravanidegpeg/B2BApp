package com.degpeg.b2bapp.presenter.interfaces

import com.degpeg.b2bapp.model.request.loginRequest

interface ILogin {
    fun postLogin(request: loginRequest?)
    fun getUsers()
}
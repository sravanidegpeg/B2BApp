package com.degpeg.b2bapp.presenter.interfaces

import com.degpeg.b2bapp.model.response.loginResponse

interface LoginMainView {
    fun getLoginDetails(response: loginResponse?)
    fun failure(msg: String?, response: String)
}
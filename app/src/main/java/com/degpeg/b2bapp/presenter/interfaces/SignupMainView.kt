package com.degpeg.b2bapp.presenter.interfaces

import com.degpeg.b2bapp.model.response.signupResponse

interface SignupMainView {
    fun getSignUpDetails(response: signupResponse?)
    fun failure(msg: String?, errorStatus: Boolean)
}
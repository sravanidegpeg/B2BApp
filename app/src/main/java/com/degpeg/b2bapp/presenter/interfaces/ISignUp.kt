package com.degpeg.b2bapp.presenter.interfaces

import com.degpeg.b2bapp.model.request.signupRequest

interface ISignUp {
    fun getSignUpDetails(request: signupRequest?)
}
package com.degpeg.b2bapp.presenter.interfaces

import com.degpeg.b2bapp.model.request.ForgotPasswordRequest
import com.degpeg.b2bapp.model.request.ResetPasswordRequest

interface IResetPassword {
    fun PostResetPasswordEmailRequest(request: ForgotPasswordRequest?)
    fun PostResetPassword(userid:String,request: ResetPasswordRequest?)
}
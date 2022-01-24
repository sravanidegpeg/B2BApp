package com.degpeg.b2bapp.presenter.interfaces

import com.degpeg.b2bapp.model.request.ResetPasswordRequest

interface IResetPassword {
    fun PostResetPasswordEmailRequest(request: ResetPasswordRequest?)
    fun PostResetPassword(id:String,request: ResetPasswordRequest?)
}
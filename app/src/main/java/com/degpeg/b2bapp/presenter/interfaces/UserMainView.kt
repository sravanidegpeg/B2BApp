package com.degpeg.b2bapp.presenter.interfaces

import com.degpeg.b2bapp.model.response.UserDetailsResponse
import com.degpeg.b2bapp.model.response.loginResponse

interface UserMainView {
    fun getUsers(userId: String?, userDetailsResponse: UserDetailsResponse?)
}
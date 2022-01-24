package com.degpeg.b2bapp.presenter.interfaces

import com.degpeg.b2bapp.model.request.UserDetailsRequest


interface IUserDetails {
    fun getUserDetails(userDetailsRequest: UserDetailsRequest?)
}
package com.degpeg.b2cappdevelopment.model.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class LoginResponse implements Serializable {

    @Expose
    @SerializedName("token")
    public String token;
}

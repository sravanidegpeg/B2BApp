package com.degpeg.b2cappdevelopment.model.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class SignUpResponse implements Serializable {
    @Expose
    @SerializedName("roles")
    public List<String> roles;
    @Expose
    @SerializedName("firstName")
    public String firstName;
    @Expose
    @SerializedName("otp")
    public String otp;
    @Expose
    @SerializedName("email")
    public String email;
    @Expose
    @SerializedName("status")
    public String status;
    @Expose
    @SerializedName("id")
    public String id;


    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getOtp() {
        return otp;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}

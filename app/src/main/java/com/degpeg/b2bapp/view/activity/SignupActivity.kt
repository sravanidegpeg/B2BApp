package com.degpeg.b2bapp.view.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.view.View
import android.view.WindowManager
import android.widget.CheckBox
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.degpeg.b2bapp.model.request.signupRequest
import com.degpeg.b2bapp.model.response.signupResponse
import com.degpeg.b2bapp.presenter.implemention.SignUpPresenter
import com.degpeg.b2bapp.presenter.interfaces.ISignUp
import com.degpeg.b2bapp.presenter.interfaces.SignupMainView
import com.example.b2bapp.R
import java.util.ArrayList

class SignupActivity: AppCompatActivity(),SignupMainView {
    var presenter: ISignUp? = null
    var request: signupRequest? = null
    var rolesList = ArrayList<String>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup_screen)
        presenter = SignUpPresenter(this)
        request = signupRequest()
        val name= findViewById<EditText>(R.id.name)
        val email = findViewById<EditText>(R.id.email)
        val password = findViewById<EditText>(R.id.password)
        val phone = findViewById<EditText>(R.id.phone)
        rolesList.add("consumer")
        findViewById<TextView>(R.id.signup_button).setOnClickListener(View.OnClickListener {
                request!!.firstName = name.text.toString()
                request!!.email = email.text.toString()
                request!!.password = password.text.toString()
                request!!.phone = phone.text.toString()
                request!!.status = "inactive"
                request!!.roles = rolesList
                presenter?.getSignUpDetails(request)

        })

        findViewById<TextView>(R.id.login_btn).setOnClickListener(View.OnClickListener {
            goToLoginScreen()
        })

    }

    private fun goToLoginScreen() {
        val i = Intent(this, LoginActivity::class.java)
        finish()
        startActivity(i)
    }

    override fun getSignUpDetails(response: signupResponse?) {
        val id: String? = response?.id
        val otp: String? = response?.otp
        Log.d("otp is",  otp + id)
        Toast.makeText(this, "otp : + $otp>>>$id", Toast.LENGTH_SHORT).show()
        val intent = Intent(this, OtpActivity::class.java)
        intent.putExtra("id", id)
        intent.putExtra("otp", otp)
        startActivity(intent)
        TODO("Not yet implemented")
    }

    override fun failure(msg: String?, errorStatus: Boolean) {
        Toast.makeText(this, "" + msg, Toast.LENGTH_SHORT).show()
        TODO("Not yet implemented")

    }
}


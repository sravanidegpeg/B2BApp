package com.degpeg.b2bapp.view.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.view.View
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
import android.text.TextUtils
import android.view.WindowManager
import android.widget.ImageView
import com.degpeg.b2bapp.SessionManager
import java.util.regex.Pattern


class SignupActivity: AppCompatActivity(),SignupMainView {
    var presenter: ISignUp? = null
    var request: signupRequest? = null
    var rolesList = ArrayList<String>()
    var userId = String()
    var session: SessionManager? = null
    var error_message:TextView?=null
    private val PASSWORD_PATTERN = Pattern.compile(
        "^" +
                "(?=.*[@#$%^&+=])" +  // at least 1 special character
                "(?=\\S+$)" +  // no white spaces
                ".{4,}" +  // at least 4 characters
                "$"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup_screen)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN);
        presenter = SignUpPresenter(this)
        request = signupRequest()
        val name= findViewById<EditText>(R.id.name)
        val email = findViewById<EditText>(R.id.email)
        val password = findViewById<EditText>(R.id.password)
        val phone = findViewById<EditText>(R.id.phone)
        error_message = findViewById(R.id.show_error_message)
        rolesList.add("consumer")
        findViewById<ImageView>(R.id.back_btn).setOnClickListener(View.OnClickListener {
            onBackPressed()
        })
        findViewById<TextView>(R.id.signup_button).setOnClickListener(View.OnClickListener {
            if (name.text.toString().isEmpty()){
                name.requestFocus()
                error_message?.visibility = View.VISIBLE
                error_message?.text = getString(R.string.enter_your_name)
            }else if (email.text.toString().isEmpty()){
                email.requestFocus()
                error_message?.visibility = View.VISIBLE
                error_message?.text = getString(R.string.enter_your_email)
            }else if (password.text.toString().isEmpty()){
                password.requestFocus()
                error_message?.visibility = View.VISIBLE
                error_message?.text = getString(R.string.enter_password)
            }else if (!PASSWORD_PATTERN.matcher(password.text.toString()).matches() ){
                password.requestFocus()
                error_message?.visibility = View.VISIBLE
                error_message?.text = getString(R.string.valid_password)
            }else if (phone.text.toString().isEmpty()){
                phone.requestFocus()
                error_message?.visibility = View.VISIBLE
                error_message?.text = getString(R.string.enter_your_phone)
            }else {
                error_message?.visibility = View.GONE
                request!!.firstName = name.text.toString()
                request!!.email = email.text.toString()
                request!!.password = password.text.toString()
                request!!.phone = phone.text.toString()
                request!!.status = "inactive"
                request!!.roles = rolesList
                presenter?.getSignUpDetails(request)
            }

        })

        findViewById<TextView>(R.id.login_btn).setOnClickListener(View.OnClickListener {
            goToLoginScreen()
        })

    }

    private fun isValidEmail(target: CharSequence?): Boolean {
        return !(TextUtils.isEmpty(target) || !Patterns.EMAIL_ADDRESS.matcher(target).matches())
    }



    override fun getSignUpDetails(response: signupResponse?) {
        userId = response?.id.toString()
        val otp: String? = response?.otp
        val shared = getSharedPreferences("B2B", MODE_PRIVATE)
        val editor = shared.edit()
        val keyChannel = ""
        editor.putString(keyChannel, userId)
        editor.apply() // commit is important here

        Log.d("otp is",  otp + userId)
        Toast.makeText(this, "otp : + $otp>>>$userId", Toast.LENGTH_SHORT).show()
        val intent = Intent(this, OtpActivity::class.java)
        intent.putExtra("id", userId)
        intent.putExtra("otp", otp)
        startActivity(intent)
        TODO("Not yet implemented")
    }

    private fun goToLoginScreen() {
        val i = Intent(this, LoginActivity::class.java)
        finish()
        startActivity(i)
    }

    override fun failure(msg: String?, errorStatus: String) {
        error_message?.visibility = View.VISIBLE
        error_message?.text = "Already Exists."
        Toast.makeText(this, "Already Exists.", Toast.LENGTH_SHORT).show()
        TODO("Not yet implemented")

    }
}


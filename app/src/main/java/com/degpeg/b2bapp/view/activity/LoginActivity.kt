package com.degpeg.b2bapp.view.activity

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.b2bapp.R
import java.util.regex.Pattern
import android.util.Log
import android.view.WindowManager
import com.degpeg.b2bapp.model.request.loginRequest
import com.degpeg.b2bapp.model.response.loginResponse
import com.degpeg.b2bapp.presenter.implemention.LoginPresenter
import com.degpeg.b2bapp.presenter.interfaces.LoginMainView





@Suppress("DEPRECATION")
class LoginActivity: AppCompatActivity(),LoginMainView {
    var userId: String? = null
    private val PASSWORD_PATTERN = Pattern.compile(
        "^" +
                "(?=.*[@#$%^&+=])" +  // at least 1 special character
                "(?=\\S+$)" +  // no white spaces
                ".{4,}" +  // at least 4 characters
                "$"
    )
    var presenter = LoginPresenter(this)
    var  request = loginRequest()
    var login_error_message:TextView?=null
    private val PREF_NAME = "B2B"
    var email:EditText?=null
    var password:EditText?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_screen)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN);
        userId = intent.getStringExtra("userid")

        Log.d("id", userId.toString())
         email = findViewById<EditText>(R.id.email)
         password = findViewById<EditText>(R.id.password)
        login_error_message = findViewById(R.id.show_login_error_message)

        findViewById<TextView>(R.id.forgot_password_btn).setOnClickListener(View.OnClickListener {
            val i = Intent(this, ForgotPasswordActivity::class.java)
            i.putExtra("id",userId)
            startActivity(i)
        })
        findViewById<TextView>(R.id.signup_button).setOnClickListener(View.OnClickListener {
            val i = Intent(this, SignupActivity::class.java)
            startActivity(i)
        })

        findViewById<TextView>(R.id.skip_btn).setOnClickListener(View.OnClickListener {
            val i = Intent(this, MainActivity::class.java)
            startActivity(i)
        })


        findViewById<TextView>(R.id.login_button).setOnClickListener(View.OnClickListener {
            when {
                validateEmail() && validatePassword() -> {
                }
            }
        })


    }

    private fun validateEmail(): Boolean {
        val emailInput: String = email?.text.toString()
        return if (emailInput.isEmpty()) {
            email?.requestFocus()
            login_error_message?.visibility = View.VISIBLE
            login_error_message?.text = getString(R.string.enter_your_email)
            false
        } else if (!Patterns.EMAIL_ADDRESS.matcher(emailInput).matches()) {
            email?.requestFocus()
            login_error_message?.visibility = View.VISIBLE
            login_error_message?.text = getString(R.string.valid_email)
            false
        } else {
            login_error_message!!.visibility = View.GONE
            request.email =email?.text.toString()
            request.password = password?.text.toString()
            presenter.postLogin(request)
            true
        }
    }

    private fun validatePassword(): Boolean {
        val passwordInput: String = password?.text.toString()
        return if (passwordInput.isEmpty()) {
            password?.requestFocus()
            login_error_message?.visibility = View.VISIBLE
            login_error_message?.text = getString(R.string.enter_password)
            false
        } else if (!PASSWORD_PATTERN.matcher(passwordInput).matches()) {
            password?.requestFocus()
            login_error_message?.visibility = View.VISIBLE
            login_error_message?.text = getString(R.string.valid_password)
            false
        } else {
            login_error_message!!.visibility = View.GONE
            request.email =email?.text.toString()
            request.password = password?.text.toString()
            presenter.postLogin(request)
            true
        }
    }

    override fun getLoginDetails(response: loginResponse?) {
        login_error_message!!.visibility= View.VISIBLE
        login_error_message!!.text = getString(R.string.login_success)
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        email?.text?.clear()
        password?.text?.clear()
    }

    override fun failure(msg: String?, response: String) {
        login_error_message!!.visibility= View.VISIBLE
        login_error_message!!.text = getString(R.string.login_error)
    }

}
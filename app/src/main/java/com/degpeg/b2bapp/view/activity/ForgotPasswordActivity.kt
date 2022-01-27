package com.degpeg.b2bapp.view.activity

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.text.InputType
import android.util.Log
import android.util.Patterns
import android.view.View
import android.view.WindowManager
import android.widget.EditText
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.degpeg.b2bapp.SessionManager
import com.degpeg.b2bapp.model.request.ForgotPasswordRequest
import com.degpeg.b2bapp.model.request.ResetPasswordRequest
import com.degpeg.b2bapp.presenter.implemention.ResetPasswordPresenter
import com.example.b2bapp.R
import java.util.regex.Pattern

class ForgotPasswordActivity: AppCompatActivity() {
    var presenter = ResetPasswordPresenter()
    var  request = ForgotPasswordRequest()
    var resetPasswordRequest = ResetPasswordRequest()
    var create_password_layout:LinearLayout?=null
    var email_layout:LinearLayout?=null
    var email:EditText?=null
    var new_password:EditText?=null
    var confirm_password:EditText?=null
    var error_message :TextView?=null
     var  session: SessionManager? = null
    private val PASSWORD_PATTERN = Pattern.compile(
        "^" +
                "(?=.*[@#$%^&+=])" +  // at least 1 special character
                "(?=\\S+$)" +  // no white spaces
                ".{4,}" +  // at least 4 characters
                "$"
    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgot_password_screen)
        session =  SessionManager(this);
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN);

        email_layout = findViewById(R.id.email_layout)
        create_password_layout = findViewById(R.id.create_password_layout)
         email = findViewById(R.id.email)
          error_message = findViewById(R.id.forgot_password_login_error)
         new_password = findViewById(R.id.new_password)
         confirm_password = findViewById(R.id.confirm_password)
        val password_error_message = findViewById<TextView>(R.id.password_login_error)


        findViewById<ImageView>(R.id.back_button).setOnClickListener(View.OnClickListener {
            onBackPressed()
        })

        findViewById<TextView>(R.id.submit_button).setOnClickListener(View.OnClickListener {
            when {
                validateEmail() -> {}
            }

        })

        findViewById<TextView>(R.id.done_button).setOnClickListener(View.OnClickListener {
            when{
                validatePassword(password_error_message!!) ->{}
            }

        })

        findViewById<ImageView>(R.id.show_new_password_btn).setOnClickListener(View.OnClickListener {
            if (new_password?.inputType!! == InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD) {
                new_password?.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
            }else{
                new_password?.inputType = InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD;
            }
        })

        findViewById<ImageView>(R.id.show_confirm_pass_btn).setOnClickListener(View.OnClickListener {
            if (confirm_password?.inputType!! == InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD) {
                confirm_password?.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
            }else{
                confirm_password?.inputType = InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD;
            }
        })
    }

    private fun validateEmail(): Boolean {
        val emailInput: String = email?.text.toString().trim()
        return if (emailInput.isEmpty()) {
            email?.requestFocus()
            error_message?.visibility = View.VISIBLE
            error_message?.text = getString(R.string.enter_your_email)
            false
        } else if (!Patterns.EMAIL_ADDRESS.matcher(emailInput).matches()) {
            email?.requestFocus()
            error_message?.visibility = View.VISIBLE
            error_message?.text = getString(R.string.valid_email)
            false
        } else {
            error_message?.visibility = View.VISIBLE
            request.email = emailInput
            request.client_api_url = ""
            presenter.PostResetPasswordEmailRequest(request)
            Handler().postDelayed({
                error_message?.text = getString(R.string.reset_password_email_sent)
                create_password_layout?.visibility = View.VISIBLE
                email_layout?.visibility = View.GONE

            },3000)
            true

        }
    }

    private fun validatePassword(error: TextView): Boolean {
        val newPasswordInput: String = new_password?.text.toString()
        val confirmPasswordInput:String = confirm_password?.text.toString()
        return if (newPasswordInput.isEmpty() && confirmPasswordInput.isEmpty()) {
            new_password?.requestFocus()
            confirm_password?.requestFocus()
            error.visibility = View.VISIBLE
            error.text = getString(R.string.enter_password)
            false
        } else if (!PASSWORD_PATTERN.matcher(newPasswordInput).matches() && !PASSWORD_PATTERN.matcher(confirmPasswordInput).matches()) {
            new_password?.requestFocus()
            confirm_password?.requestFocus()
            error.visibility = View.VISIBLE
            error.text = getString(R.string.valid_password)
            false
        } else {
            resetPasswordRequest.userId =session?.getuserid()
            Log.e("session userid",session?.getuserid().toString())
            resetPasswordRequest.password = newPasswordInput
            presenter.PostResetPassword(session?.getuserid().toString(),resetPasswordRequest)
            Handler().postDelayed({
                error.visibility = View.VISIBLE
                error.text = getString(R.string.reset_password_success_message)
                create_password_layout?.visibility = View.VISIBLE
                email_layout?.visibility = View.GONE
                val i = Intent(this, LoginActivity::class.java)
                finish()
                startActivity(i)
                                  }, 3000)

            true
        }
    }
}
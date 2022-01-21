package com.degpeg.b2bapp.view.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.b2bapp.R

class LoginActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_screen)
        findViewById<TextView>(R.id.forgot_password_btn).setOnClickListener(View.OnClickListener {
            val i = Intent(this, ForgotPasswordActivity::class.java)
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
            val i = Intent(this, MainActivity::class.java)
            startActivity(i)
        })


    }
}
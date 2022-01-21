package com.example.b2bapp

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class SignupActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup_screen)
        findViewById<TextView>(R.id.signup_button).setOnClickListener(View.OnClickListener {
           goToLoginScreen()
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
}
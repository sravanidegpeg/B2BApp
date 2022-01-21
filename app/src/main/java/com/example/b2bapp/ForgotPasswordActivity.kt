package com.example.b2bapp

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ForgotPasswordActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgot_password_screen)
        val verification_code_layout = findViewById<LinearLayout>(R.id.verification_code_screen) as LinearLayout
        val email_layout = findViewById<LinearLayout>(R.id.email_layout) as LinearLayout
        val create_password_layout = findViewById<LinearLayout>(R.id.create_password_layout) as LinearLayout


        findViewById<TextView>(R.id.submit_button).setOnClickListener(View.OnClickListener {
            verification_code_layout.visibility = View.VISIBLE
            email_layout.visibility = View.GONE
        })

        findViewById<TextView>(R.id.verify_otp_btn).setOnClickListener(View.OnClickListener {
            create_password_layout.visibility = View.VISIBLE
            verification_code_layout.visibility = View.GONE
        })

        findViewById<TextView>(R.id.done_button).setOnClickListener(View.OnClickListener {
            val i = Intent(this, LoginActivity::class.java)
            finish()
            startActivity(i)
        })
    }
}
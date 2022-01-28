package com.degpeg.b2bapp.view.activity

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.example.b2bapp.R

class AddSessionActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_session)
        val sessionBackBtn= findViewById<ImageView>(R.id.session_back_btn)
        sessionBackBtn.setOnClickListener(View.OnClickListener {
            onBackPressed()
        })
    }
}
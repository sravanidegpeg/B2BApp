package com.degpeg.b2bapp.view.activity

import android.content.Intent
import android.nfc.Tag
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.b2bapp.R

class OtpActivity:AppCompatActivity() {
    var otp: String? = null
    var otpvalue:String?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activtiy_otp_screen)
        otp = intent.getStringExtra("otp")
        Log.d("otp", otp.toString())
        findViewById<ImageView>(R.id.back_btn).setOnClickListener(View.OnClickListener {
            onBackPressed()
        })
        findViewById<TextView>(R.id.verify_otp_btn).setOnClickListener(View.OnClickListener {
            val otp_edit_box1 = findViewById<EditText>(R.id.otp_edit_box1)
            val otp_edit_box2 = findViewById<EditText>(R.id.otp_edit_box2)
            val otp_edit_box3 = findViewById<EditText>(R.id.otp_edit_box3)
            val otp_edit_box4 = findViewById<EditText>(R.id.otp_edit_box4)
            val otp_edit_box5 = findViewById<EditText>(R.id.otp_edit_box5)
            otpvalue = otp_edit_box1.text.toString() + otp_edit_box2.text.toString()+ otp_edit_box3.text.toString()+ otp_edit_box4.text.toString()+otp_edit_box5.text.toString()
            Log.d("otpvalue" , otpvalue.toString())
             if (otp.equals(otpvalue)){
                 Toast.makeText(this, "activation success", Toast.LENGTH_SHORT).show()
                 val intent = Intent(this, LoginActivity::class.java)
                 startActivity(intent)
             }else{
                 Toast.makeText(this, "Invalid otp", Toast.LENGTH_SHORT).show()
             }
        })


    }

}
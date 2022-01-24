package com.degpeg.b2bapp.view.activity

import android.content.Intent
import android.nfc.Tag
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import android.view.WindowManager
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.b2bapp.R
import org.w3c.dom.Text

class OtpActivity:AppCompatActivity() {
    var otp: String? = null
    var otpvalue:String?=null
    var userId:String?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activtiy_otp_screen)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN);
        otp = intent.getStringExtra("otp")
        userId = intent.getStringExtra("id")
        Log.d("otp", otp.toString())
        findViewById<ImageView>(R.id.back_btn).setOnClickListener(View.OnClickListener {
            onBackPressed()
        })

        val otp_edit_box1 = findViewById<EditText>(R.id.otp_edit_box1)
        val otp_edit_box2 = findViewById<EditText>(R.id.otp_edit_box2)
        val otp_edit_box3 = findViewById<EditText>(R.id.otp_edit_box3)
        val otp_edit_box4 = findViewById<EditText>(R.id.otp_edit_box4)
        val otp_edit_box5 = findViewById<EditText>(R.id.otp_edit_box5)
        val otp_error_message = findViewById<TextView>(R.id.show_otp_error_message)

        findViewById<TextView>(R.id.verify_otp_btn).setOnClickListener(View.OnClickListener {
            otpvalue = otp_edit_box1.text.toString() + otp_edit_box2.text.toString()+ otp_edit_box3.text.toString()+ otp_edit_box4.text.toString()+otp_edit_box5.text.toString()
            Log.d("otpvalue" , otpvalue.toString())
            if (otp_edit_box1.text.toString().isEmpty()){
                otp_edit_box1.requestFocus()
                otp_error_message.visibility = View.VISIBLE
                otp_error_message.text = getString(R.string.otp_error_message)
            }else if (otp_edit_box2.text.toString().isEmpty()){
                otp_edit_box2.requestFocus()
                otp_error_message.visibility = View.VISIBLE
                otp_error_message.text = getString(R.string.otp_error_message)
            }else if (otp_edit_box3.text.toString().isEmpty()){
                otp_edit_box3.requestFocus()
                otp_error_message.visibility = View.VISIBLE
                otp_error_message.text = getString(R.string.otp_error_message)
            }else if (otp_edit_box4.text.toString().isEmpty()){
                otp_edit_box4.requestFocus()
                otp_error_message.visibility = View.VISIBLE
                otp_error_message.text = getString(R.string.otp_error_message)
            }else if (otp_edit_box5.text.toString().isEmpty()){
                otp_edit_box5.requestFocus()
                otp_error_message.visibility = View.VISIBLE
                otp_error_message.text = getString(R.string.otp_error_message)
            }else if (otp.equals(otpvalue)){
                Handler().postDelayed({
                    otp_error_message.visibility = View.VISIBLE
                    otp_error_message.text = getString(R.string.otp_success)
                    val intent = Intent(this, LoginActivity::class.java)
                    intent.putExtra("userid",userId)
                    startActivity(intent)
                }, 3000)

             }else{
                 otp_error_message.visibility = View.VISIBLE
                 otp_error_message.text = getString(R.string.otp_failure)
             }
        })

    }

}
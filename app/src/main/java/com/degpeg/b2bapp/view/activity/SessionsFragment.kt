package com.degpeg.b2bapp.view.activity

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.b2bapp.R

class SessionsFragment: Fragment() {
    fun newInstance(someInt: Int): SessionsFragment? {
        val myFragment = SessionsFragment()
        val args = Bundle()
        args.putInt("someInt", someInt)
        myFragment.arguments = args
        return myFragment
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.activity_session_screen, container, false)
        val sessionBtn = view.findViewById<TextView>(R.id.add_session_btn)
        sessionBtn.setOnClickListener(View.OnClickListener {
            val sessionIntent = Intent(context,AddSessionActivity::class.java)
            startActivity(sessionIntent)
        })
        return view
    }
}
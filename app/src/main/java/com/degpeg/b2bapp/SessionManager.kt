package com.degpeg.b2bapp

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import android.util.Log


class SessionManager(applicationContext: Context) {
    var pref: SharedPreferences? = null
    var editor: SharedPreferences.Editor? = null
    var _context: Context? = null
    val KEY_userid = String()
    private val PREF_NAME = "AndroidHivePref"
    var PRIVATE_MODE = 0
    @SuppressLint("CommitPrefEdits")
    fun SessionManager(context: Context) {
        this._context = context
        pref = _context!!.getSharedPreferences(PREF_NAME,PRIVATE_MODE)
        editor = pref!!.edit()
    }

    fun createLoginSession(userid: String?) {
        // Storing userid in pref
        editor!!.putString(KEY_userid, userid)
        Log.d("sharedpreferences",KEY_userid)
        editor!!.commit()
    }

}
package com.degpeg.b2bapp

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager


class SessionManager(cntx: Context?) {
    private val prefs: SharedPreferences
    fun setuserid(userid: String?) {
        prefs.edit().putString("userid", userid).commit()
    }

    fun getuserid(): String? {
        return prefs.getString("userid", "")
    }

    init {
        // TODO Auto-generated constructor stub
        prefs = PreferenceManager.getDefaultSharedPreferences(cntx)
    }
}
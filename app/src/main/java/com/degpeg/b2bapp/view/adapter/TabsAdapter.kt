package com.degpeg.b2bapp.view.adapter

import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.degpeg.b2bapp.view.activity.HomeFragment
import com.degpeg.b2bapp.view.activity.SessionsFragment


class TabsAdapter(fm: FragmentManager?) : FragmentStatePagerAdapter(fm!!) {
    var homeFragment: HomeFragment? = null
    var sessionsFragment:SessionsFragment?=null
    override fun getCount(): Int {
        return 2
    }

    override fun getItem(position: Int): Fragment {
        Log.e("pos", position.toString())
        if (position == 0) {
            homeFragment = HomeFragment().newInstance(position)
            return homeFragment!!
        }else if (position ==1){
            sessionsFragment = SessionsFragment().newInstance(position)
            return sessionsFragment!!
        }
        return sessionsFragment!!
    }
}
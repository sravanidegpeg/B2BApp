package com.degpeg.b2bapp.view.adapter

import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.degpeg.b2bapp.view.activity.HomeFragment


class TabsAdapter(fm: FragmentManager?) : FragmentStatePagerAdapter(fm!!) {
    var homeFragment: HomeFragment? = null
    override fun getCount(): Int {
        return 1
    }

    override fun getItem(position: Int): Fragment {
        Log.e("pos", position.toString())
        if (position == 0) {
            homeFragment = HomeFragment().newInstance(position)
            return homeFragment!!
        }
        return homeFragment!!
    }
}
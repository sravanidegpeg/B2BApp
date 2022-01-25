package com.degpeg.b2bapp.view.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter


class TabsAdapter(fm: FragmentManager?) : FragmentStatePagerAdapter(fm!!) {

    override fun getCount(): Int {
        return 13
    }

    override fun getItem(position: Int): Fragment {
        TODO("Not yet implemented")
    }
}
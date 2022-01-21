package com.example.b2bapp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.viewpager.widget.PagerAdapter
import com.degpeg.b2bapp.view.activity.SplashImages
import com.squareup.picasso.Picasso

class DotIndicatorPagerAdapter(var context:Context,var splashImagesList:ArrayList<SplashImages>) : PagerAdapter() {


    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val item = LayoutInflater.from(container.context).inflate(R.layout.activity_splash_screen_view, container,
            false)
        container.addView(item)
        val splashImage = item.findViewById<ImageView>(R.id.splash_image)
        val splashTitle = item.findViewById<TextView>(R.id.splash_title)
        val splashContent = item.findViewById<TextView>(R.id.splash_content)
        Picasso.get().load(splashImagesList[position].image).into(splashImage)
        splashTitle.text = splashImagesList[position].title
        splashContent.text = splashImagesList[position].content
        return item
    }
    override fun getCount(): Int {
        return splashImagesList.size
        TODO("Not yet implemented")
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object`
        TODO("Not yet implemented")
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }
}
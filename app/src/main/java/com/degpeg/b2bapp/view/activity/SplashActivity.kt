package com.degpeg.b2bapp.view.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.example.b2bapp.DotIndicatorPagerAdapter
import com.example.b2bapp.R
import com.tbuonomo.viewpagerdotsindicator.DotsIndicator

class SplashActivity : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        val splashImagesList = ArrayList<SplashImages>()
        setContentView(R.layout.activity_splash_screen)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN);
        val viewpager = findViewById<ViewPager>(R.id.view_pager)
        val dotsindicator = findViewById<DotsIndicator>(R.id.dots_indicator)
        val nextButton = findViewById<ImageView>(R.id.next_btn)
        splashImagesList.add(
            SplashImages(
                R.drawable.splash_screen_one,getString(R.string.splash_title_one),getString(
                    R.string.splash_content_one
                ))
        )
        splashImagesList.add(
            SplashImages(
                R.drawable.splash_screen_two,getString(R.string.splash_title_two),getString(
                    R.string.splash_content_two
                ))
        )
        splashImagesList.add(
            SplashImages(
                R.drawable.splash_screen_three,getString(R.string.splash_title_three),getString(
                    R.string.splash_content_three
                ))
        )

        with(viewpager) {
            viewpager.adapter = DotIndicatorPagerAdapter(context,splashImagesList)
            dotsindicator.setViewPager(this)
            nextButton.setOnClickListener(View.OnClickListener {
                val intent = Intent(context, LoginActivity::class.java)
                startActivity(intent)
                finish()
            })

        }

    }
}
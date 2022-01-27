package com.degpeg.b2bapp.view.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.annotation.IdRes
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.viewpager.widget.ViewPager
import androidx.drawerlayout.widget.DrawerLayout
import android.graphics.drawable.ColorDrawable
import android.util.Log
import android.view.View
import android.view.WindowManager
import android.widget.*
import androidx.appcompat.app.ActionBar
import androidx.appcompat.widget.Toolbar
import androidx.viewpager.widget.ViewPager.OnPageChangeListener
import com.degpeg.b2bapp.view.adapter.MenuAdapter
import com.degpeg.b2bapp.view.adapter.TabsAdapter
import com.example.b2bapp.R
import it.sephiroth.android.library.bottomnavigation.BottomNavigation
import java.lang.Exception


class MainActivity : AppCompatActivity(), BottomNavigation.OnMenuItemSelectionListener{
    private var mBottomNavigation: BottomNavigation? = null
    private var mViewPager: ViewPager? = null
    private var tabsAdapter: TabsAdapter? = null
    private var mDrawerLayout: DrawerLayout? = null
    var menu_btn: ImageView? = null
    var page_title: TextView? = null
    private var mDrawerToggle: ActionBarDrawerToggle? = null
    var previous_page = false


    private fun openNavigation() {
        if (mDrawerLayout!!.isDrawerOpen(GravityCompat.START)) mDrawerLayout!!.closeDrawer(
            GravityCompat.START,
            true
        ) else mDrawerLayout!!.openDrawer(GravityCompat.START, true)
    }

    private fun setupActionBar() {
        supportActionBar!!.setHomeButtonEnabled(false)
        supportActionBar!!.setDisplayShowTitleEnabled(false)
        supportActionBar!!.setDisplayShowCustomEnabled(true)
        supportActionBar!!.setIcon(ColorDrawable(resources.getColor(android.R.color.transparent)))
        val layoutParams: ActionBar.LayoutParams = ActionBar.LayoutParams(
            ActionBar.LayoutParams.MATCH_PARENT,
            ActionBar.LayoutParams.MATCH_PARENT
        )
        val inflater = layoutInflater
        val v: View = inflater.inflate(R.layout.action_bar_title, null)
        page_title = v.findViewById(R.id.page_title)
        menu_btn = v.findViewById(R.id.btn_menu) as ImageView
        menu_btn!!.setOnClickListener(View.OnClickListener {
            openNavigation()
        })


        supportActionBar!!.setCustomView(v, layoutParams)
        val parent: Toolbar = v.parent as Toolbar
        parent.setContentInsetsAbsolute(0, 0)

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mDrawerLayout = findViewById<View>(R.id.drawer_layout) as DrawerLayout
        mBottomNavigation = findViewById<View>(R.id.BottomNavigation) as BottomNavigation
//        mBottomNavigation.setOnMenuItemClickListener(this)
        tabsAdapter = TabsAdapter(supportFragmentManager)
        mViewPager = findViewById<View>(R.id.viewPager) as ViewPager
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN);
        mViewPager!!.adapter = tabsAdapter
        mViewPager!!.addOnPageChangeListener(object : OnPageChangeListener {
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
            }

            override fun onPageSelected(position: Int) {
                try {
                    when (position) {
                        0 -> mBottomNavigation!!.setSelectedIndex(
                            position,
                            false
                        )
                        1 -> mBottomNavigation!!.setSelectedIndex(
                            position,
                            false
                        )
                        2 -> mBottomNavigation!!.setSelectedIndex(
                            position,
                            false
                        )
                        3 -> mBottomNavigation!!.setSelectedIndex(
                            2,
                            false
                        )
                        4 -> mBottomNavigation!!.setSelectedIndex(3, false)
                    }
                } catch (ex: Exception) {
                    ex.printStackTrace()
                }
                setHeader(position)
            }

            override fun onPageScrollStateChanged(state: Int) {}
        })
        val menuItems: ArrayList<MenuItem> = ArrayList()

        menuItems.add(MenuItem(getString(R.string.home),R.drawable.menu_home))
        menuItems.add(MenuItem(getString(R.string.sessions), R.drawable.sessions_inactive))
        menuItems.add(MenuItem(getString(R.string.channels),  R.drawable.channels_inactive))
        menuItems.add(MenuItem(getString(R.string.calender),  R.drawable.calender_inactive))
        menuItems.add(MenuItem(getString(R.string.chat),  R.drawable.chat_icon))
        menuItems.add(MenuItem(getString(R.string.analytics),  R.drawable.chat_icon))
        menuItems.add(MenuItem(getString(R.string.studio),  R.drawable.studio_inactive))
        menuItems.add(MenuItem(getString(R.string.faqs),  R.drawable.faq_inactive))

        val menuAdapter = MenuAdapter(this, menuItems)
        val listView: ListView = findViewById<View>(R.id.menu_list) as ListView
        listView.adapter = menuAdapter
        listView.onItemClickListener = AdapterView.OnItemClickListener { _, _, i, _ ->
            mDrawerLayout!!.closeDrawer(GravityCompat.START, true)
            Log.e("pos click", i.toString())
            when (i) {
                0 -> mViewPager!!.currentItem = 5
                1 -> mViewPager!!.currentItem = 6
                2 -> mViewPager!!.currentItem = 7
                3 -> mViewPager!!.currentItem = 8
                4 -> mViewPager!!.currentItem = 9
                5 -> {

                }
            }
        }
        mDrawerLayout!!.setScrimColor(resources.getColor(android.R.color.transparent))
        val toolbar: Toolbar = findViewById<View>(R.id.my_awesome_toolbar) as Toolbar
        setSupportActionBar(toolbar)
        setupActionBar()
        setHeader(0)
        drawerView = findViewById<View>(R.id.drawerView) as LinearLayout
        mainView = findViewById<View>(R.id.mainView) as RelativeLayout
        mDrawerToggle = object :
            ActionBarDrawerToggle(this, mDrawerLayout, null, R.string.app_name, R.string.app_name) {
            override fun onDrawerClosed(drawerView: View) {
                supportInvalidateOptionsMenu()
            }

            override fun onDrawerOpened(drawerView: View) {
                supportInvalidateOptionsMenu()
            }

            override fun onDrawerSlide(drawerView: View, slideOffset: Float) {
                super.onDrawerSlide(drawerView, slideOffset)
                mainView!!.translationX = slideOffset * drawerView.getWidth()
                mDrawerLayout!!.bringChildToFront(drawerView)
                mDrawerLayout!!.requestLayout()
            }
        }
        mDrawerLayout!!.setDrawerListener(mDrawerToggle)
    }

    var drawerView: LinearLayout? = null
    var mainView: RelativeLayout? = null
    override fun onMenuItemSelect(@IdRes i: Int, i1: Int, b: Boolean) {
        Log.e("i", i1.toString())
        // previous_page = mViewPager.getCurrentItem();
        if (i1 == 3) mViewPager!!.currentItem = 4 else mViewPager!!.currentItem = i1
    }

    override fun onMenuItemReselect(@IdRes i: Int, i1: Int, b: Boolean) {
        //   Log.e("previous",String.valueOf(previous_page));
        if (i1 == 2 && !previous_page && mViewPager!!.currentItem != 3) {
            try {
                //tabsAdapter.newsFragment.get_news("0")
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
        previous_page = false
    }


    override fun onDestroy() {
        super.onDestroy()
    }

    private fun setHeader(pos: Int) {
        page_title!!.visibility = View.INVISIBLE
        menu_btn?.visibility = View.VISIBLE
       // back_btn.setVisibility(View.GONE)
        when (pos) {
            0 -> {
                page_title!!.visibility = View.VISIBLE
                page_title!!.text = getString(R.string.degpeg_title)
            }
            2 -> {
                page_title!!.visibility = View.VISIBLE
                page_title!!.text = getString(R.string.degpeg_title)
            }
            3 -> {
                page_title!!.visibility = View.VISIBLE
                page_title!!.text = getString(R.string.degpeg_title)
                menu_btn?.visibility = View.GONE
//                back_btn.setVisibility(View.VISIBLE)
            }
            5 -> {
                page_title!!.visibility = View.VISIBLE
                page_title!!.text = getString(R.string.degpeg_title)
            }
            6 -> {
                page_title!!.visibility = View.VISIBLE
                page_title!!.text = getString(R.string.degpeg_title)
            }
            7 -> {
                page_title!!.visibility = View.VISIBLE
                page_title!!.text = getString(R.string.degpeg_title)
            }
            8 -> {
                page_title!!.visibility = View.VISIBLE
                page_title!!.text = getString(R.string.degpeg_title)
            }
            9 -> {
                page_title!!.visibility = View.VISIBLE
                page_title!!.text = getString(R.string.degpeg_title)
            }
            10 -> {
                page_title!!.visibility = View.VISIBLE
                page_title!!.text = getString(R.string.degpeg_title)
                menu_btn?.visibility = View.GONE
                //back_btn.setVisibility(View.VISIBLE)
            }
            11 -> {
                page_title!!.visibility = View.VISIBLE
                page_title!!.text = getString(R.string.degpeg_title)
                menu_btn?.visibility = View.GONE
                //back_btn.setVisibility(View.VISIBLE)
            }
            12 -> {
                page_title!!.visibility = View.VISIBLE
                page_title!!.text = getString(R.string.degpeg_title)
                menu_btn?.setVisibility(View.GONE)
                //back_btn.setVisibility(View.VISIBLE)
            }
            else -> {}
        }
    }

    override fun onBackPressed() {
        // your code.
        previous_page = false
        if (mDrawerLayout!!.isDrawerOpen(GravityCompat.START)) mDrawerLayout!!.closeDrawer(
            GravityCompat.START,
            true
        ) else if (mViewPager!!.currentItem != 0) {
            if (mViewPager!!.currentItem == 3) {
                previous_page = true
                mViewPager!!.setCurrentItem(2, true)
            } else if (mViewPager!!.currentItem == 11 || mViewPager!!.currentItem == 12) mViewPager!!.setCurrentItem(
                4,
                true
            ) else mViewPager!!.setCurrentItem(0, false)
        } else finish()
    }


}



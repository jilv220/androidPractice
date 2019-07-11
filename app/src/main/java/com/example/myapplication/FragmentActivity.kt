package com.example.myapplication
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.example.myapplication.Adapter.*
import com.example.myapplication.Util.FragmentTranscationUtil
import com.example.myapplication.Util.ViewPagerUtil
import com.example.myapplication.fragments.HomeFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.fragmentmain.*

class FragmentActivity : AppCompatActivity(){

    lateinit var toolbar: androidx.appcompat.app.ActionBar
    lateinit var myViewPager : ViewPager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragmentmain)

        // init toolbar
        toolbar = supportActionBar!!

        // pass a listener func to listen the bottomNavigation bar
        navigation_view.setOnNavigationItemSelectedListener (mOnNavigationItemSelectedListener)

        //add the homepage fragment
        FragmentTranscationUtil().openFragment(HomeFragment(),R.id.fragment_container,supportFragmentManager)

        // viewpager init
        myViewPager = fragment_container       // no need to find view by id in kotlin, kotlin handles it for u
        ViewPagerUtil().init(myViewPager,TabAdapter(supportFragmentManager),TabViewOnPageChangeListener())
        myViewPager.offscreenPageLimit = 2

    }

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener {
        item ->

        when (item.itemId) {

            R.id.navigation_home -> {
                toolbar.title = "home"
                myViewPager.currentItem = HOME
                return@OnNavigationItemSelectedListener true  // lambda use annotation to return to the outside function
            }

            R.id.navigation_dashboard -> {
                toolbar.title = "dashboard"
                myViewPager.currentItem = DASHBOARD
                return@OnNavigationItemSelectedListener true
            }

            R.id.navigation_notifications -> {
                toolbar.title = "notification"
                myViewPager.currentItem = NOTIFICATION
                return@OnNavigationItemSelectedListener true
            }

            R.id.detailed_calculation -> {
                toolbar.title = "calculation"
                myViewPager.currentItem = CALCULATION
                return@OnNavigationItemSelectedListener true
            }

            else -> {
                return@OnNavigationItemSelectedListener false
            }
        }
    }

    private inner class TabViewOnPageChangeListener : ViewPager.OnPageChangeListener{  // must implement all three methods
        override fun onPageScrollStateChanged(state: Int) {

        }

        override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {

        }

        override fun onPageSelected(position: Int) {
            // viewPager adapter binding
            when (position) {
                HOME -> navigation_view.selectedItemId = R.id.navigation_home
                DASHBOARD -> navigation_view.selectedItemId = R.id.navigation_dashboard
                NOTIFICATION -> navigation_view.selectedItemId = R.id.navigation_notifications
                CALCULATION -> navigation_view.selectedItemId = R.id.detailed_calculation
            }

            toolbar.title = TabAdapter(supportFragmentManager).getPageTitle(position)
        }
    }

}


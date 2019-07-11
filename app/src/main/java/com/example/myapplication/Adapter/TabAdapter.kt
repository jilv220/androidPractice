package com.example.myapplication.Adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.myapplication.fragments.CalculationFragment
import com.example.myapplication.fragments.DashBoardFragment
import com.example.myapplication.fragments.HomeFragment
import com.example.myapplication.fragments.NotificationFragment

const val HOME = 0
const val DASHBOARD = 1
const val NOTIFICATION = 2
const val CALCULATION = 3

const val HOME_TITLE = "home"
const val DASHBOARD_TITLE = "dashboard"
const val NOTIFICATION_TITLE = "notification"
const val CALCULATION_TITLE ="calculation"


class TabAdapter : FragmentPagerAdapter {

    var fragments  = mutableListOf<Fragment>()

    constructor(fm: FragmentManager):super(fm) {

        fragments.add(HomeFragment())
        fragments.add(DashBoardFragment())
        fragments.add(NotificationFragment())
        fragments.add(CalculationFragment())
    }

    override fun getItem(position: Int) :Fragment {

        when(position) {
            HOME -> {
                return fragments[HOME]
            }
            DASHBOARD -> {
                return fragments[DASHBOARD]
            }
            NOTIFICATION -> {
                return fragments[NOTIFICATION]
            }
            CALCULATION -> {
                return fragments[CALCULATION]
            }
            else -> {
                return fragments[0]  // error
            }
        }
    }

    override fun getCount():Int {

        return fragments.size
    }

    override fun getPageTitle(position: Int): CharSequence? {

        when(position) {
            HOME -> {
                return HOME_TITLE
            }
            DASHBOARD -> {
                return DASHBOARD_TITLE
            }
            NOTIFICATION -> {
                return NOTIFICATION_TITLE
            }
            CALCULATION -> {
                return CALCULATION_TITLE
            }
            else -> {
                return "Error"  // error
            }
        }

    }

}
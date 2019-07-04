package com.example.myapplication.Util

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager

class FragmentTranscationUtil {

    fun openFragment(fragment : Fragment, container: Int, fm: FragmentManager) {

        val transaction = fm.beginTransaction()
        transaction.replace(container, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }
}
package com.example.myapplication.fragmentsViewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class HomeFragmentViewModel : ViewModel() {

    val randInt = MutableLiveData<Int>()

    fun generateRandomNumber() {
        randInt.value = (0..10).random()
    }

}


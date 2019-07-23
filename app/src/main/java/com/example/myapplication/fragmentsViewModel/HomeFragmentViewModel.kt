package com.example.myapplication.fragmentsViewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class HomeFragmentViewModel : ViewModel() {

    val randInt = MutableLiveData<Int>()
    val arrayToSort = mutableListOf(1,8,4,9,7,11,23,18)

    fun generateRandomNumber() {
        randInt.value = (0..10).random()
    }

    private fun swap(array : MutableList<Int>, first : Int, second : Int) {

        var firstElement = array[first]
        var secondElement = array[second]

        // swap element
        firstElement = firstElement xor secondElement      // a = a xor b
        secondElement = firstElement xor secondElement     // b = a xor b xor b = a
        firstElement = firstElement xor secondElement      // a = a xor b xor a = b

        // reassign the elements
        array[first] = firstElement
        array[second] = secondElement

    }

    private fun partition(array: MutableList<Int>, pivotIndex : Int, rightmostIndex : Int) : Int {

        val pivot = array[pivotIndex]  // first element as pivot
        var storedIndex = pivotIndex

        for (i in (pivotIndex + 1) .. rightmostIndex) {

            if (array[i] < pivot) {
                storedIndex += 1
                swap(array,storedIndex,i)
            }
        }

        swap(array,pivotIndex,storedIndex)

        return storedIndex
    }

    fun quickSort(array: MutableList<Int>, low : Int, high : Int) {

        if (low < high) {
            val sortedPivot = partition(array, low, high)  // the index of the sorted pivot
            quickSort(array, low, sortedPivot - 1)
            quickSort(array, sortedPivot + 1, high)
        }

    }

}


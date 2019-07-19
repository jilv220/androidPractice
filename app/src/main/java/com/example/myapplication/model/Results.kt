package com.example.myapplication.model


data class Results(val code : Int,
                   val message : String,
                   val result : MutableList<Result>)


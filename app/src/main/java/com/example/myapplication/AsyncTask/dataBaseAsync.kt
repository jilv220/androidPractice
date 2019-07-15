package com.example.myapplication.AsyncTask

import android.content.Context
import android.os.AsyncTask
import com.example.myapplication.UserDataBase
import com.example.myapplication.netModel.User

class dataBaseAsync(mUser: User) : AsyncTask<Context, Int, String>() {

    var user = mUser

    override fun doInBackground(vararg params: Context?): String {

        UserDataBase
                .getInstance(params[0])
                .userDao
                .insert(user)

        return "success"
    }



}


package com.example.myapplication.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.myapplication.AsyncTask.DataBaseAsync
import com.example.myapplication.R
import com.example.myapplication.databinding.Fragment4Binding
import com.example.myapplication.netModel.User

class CalculationFragment : Fragment(), View.OnClickListener {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {

        var binding: Fragment4Binding = DataBindingUtil.inflate(inflater,R.layout.fragment_4,container,false)
        binding.clickListener = this

        return binding.root
    }

    override fun onClick(view: View?) {

        var user = User()
        user.name = "Bruno"
        user.age = 999

        Toast.makeText(context,"开始写入数据库", Toast.LENGTH_SHORT).show()
        DataBaseAsync(user)
        Toast.makeText(context,"数据库写入完毕", Toast.LENGTH_SHORT).show()

    }

}


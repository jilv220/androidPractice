package com.example.myapplication.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.myapplication.R
import com.example.myapplication.databinding.Fragment1Binding
import com.example.myapplication.event.eventMessage
import com.example.myapplication.fragmentsViewModel.HomeFragmentViewModel
import org.greenrobot.eventbus.EventBus


var RANDOM_BT_CLICKED = false

class HomeFragment : Fragment(), View.OnClickListener {

    var viewModel = HomeFragmentViewModel()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        var binding: Fragment1Binding = DataBindingUtil.inflate(inflater,R.layout.fragment_1,container,false)

        binding.viewModel = viewModel
        binding.onClickListener = this
        binding.lifecycleOwner = this

        Log.e("viewModel","Binding succeed")


        return binding.root
    }

    override fun onClick(v: View?) {

        when (v?.id) {

            R.id.bt_generate_random -> {
                EventBus.getDefault().post(eventMessage("HomeFragment generates a random number"))
                RANDOM_BT_CLICKED = true
                viewModel.generateRandomNumber()
            }

        }
    }

}

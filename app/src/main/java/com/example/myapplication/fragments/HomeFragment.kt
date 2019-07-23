package com.example.myapplication.fragments

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.myapplication.R
import com.example.myapplication.RetrofitActivity
import com.example.myapplication.databinding.Fragment1Binding
import com.example.myapplication.event.EventMessage
import com.example.myapplication.fragmentsViewModel.HomeFragmentViewModel
import org.greenrobot.eventbus.EventBus

class HomeFragment : Fragment(), View.OnClickListener {

    var viewModel = HomeFragmentViewModel()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        var binding: Fragment1Binding = DataBindingUtil.inflate(inflater,R.layout.fragment_1,container,false)

        binding.viewModel = viewModel
        binding.onClickListener = this
        binding.onRetrofitClickListener = this
        binding.lifecycleOwner = this

        Log.e("viewModel","Binding succeed")

        return binding.root
    }

    override fun onClick(v: View?) {

        when (v?.id) {

            R.id.bt_generate_random -> {
                EventBus.getDefault().post(EventMessage("HomeFragment generates a random number"))
                viewModel.generateRandomNumber()
            }

            R.id.bt_go_to_retrofit_test -> {
                val intent = Intent()
                intent.setClass(v.context, RetrofitActivity::class.java)
                startActivity(intent)
            }

            R.id.bt_quick_sort -> {
                viewModel.quickSort(viewModel.arrayToSort,0,viewModel.arrayToSort.size - 1)

                for (i in viewModel.arrayToSort.indices) {
                    Log.e("element in array is :" ,viewModel.arrayToSort[i].toString())
                }
            }

        }
    }

}

package com.example.myapplication.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.myapplication.R
import com.example.myapplication.UserDataBase
import com.example.myapplication.databinding.Fragment4Binding
import com.example.myapplication.netModel.User
import java.util.concurrent.Callable
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import java.util.concurrent.FutureTask

class CalculationFragment : Fragment(), View.OnClickListener {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {

        var binding: Fragment4Binding = DataBindingUtil.inflate(inflater,R.layout.fragment_4,container,false)
        binding.clickListener = this

        return binding.root
    }

    override fun onClick(view: View?) {

        when (view?.id) {

            R.id.button -> {
                // create user object
                var user = User()
                user.name = "Bruno"
                user.age = 999

                //create new thread
                var runnable = Runnable { UserDataBase.getInstance(context).userDao.insert(user) }
                var futureTask  = FutureTask(runnable,"success")
                var executor : ExecutorService = Executors.newFixedThreadPool(1)

                Toast.makeText(context,"开始写入数据库", Toast.LENGTH_SHORT).show()
                executor.execute(futureTask)
                Toast.makeText(context,"数据库写入完毕", Toast.LENGTH_SHORT).show()
            }

            R.id.bt_insertTest -> {

                var user: String
                var callable = Callable<String> { UserDataBase.getInstance(context).getUserName(0) }
                var futureTask = FutureTask(callable)
                var executor : ExecutorService = Executors.newFixedThreadPool(1)
                executor.execute(futureTask)

                user = futureTask.get()

                if (futureTask.isDone) {
                    executor.shutdown()
                }

                Toast.makeText(context, "用户 : $user", Toast.LENGTH_SHORT).show()
            }

        }
    }

}


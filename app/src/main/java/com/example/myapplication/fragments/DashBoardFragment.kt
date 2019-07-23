package com.example.myapplication.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.myapplication.R
import com.example.myapplication.event.EventMessage
import kotlinx.android.synthetic.main.fragment_2.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

class DashBoardFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {

        val view = inflater.inflate(R.layout.fragment_2,container,false)

        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this)
        }

        return view
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun oneventmessege(message : EventMessage) {

        receiver.text = message.stringMessage

    }

    override fun onDestroy() {

        super.onDestroy()
        EventBus.getDefault().unregister(this)
    }
}


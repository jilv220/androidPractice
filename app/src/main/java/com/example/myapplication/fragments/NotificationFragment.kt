package com.example.myapplication.fragments

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.myapplication.R
import com.example.myapplication.databinding.Fragment3Binding
import com.example.myapplication.fragmentsViewModel.NotificationFragmentViewModel
import kotlinx.android.synthetic.main.fragment_3.*

val PICK_IMAGE = 7
val PERMISSION_REQUEST_CODE = 8

class NotificationFragment : Fragment(),View.OnClickListener {

    var viewModel = NotificationFragmentViewModel()
    var permissions : Array<String> = arrayOf(
            "android.permission.READ_EXTERNAL_STORAGE"
            ,"android.permission.WRITE_EXTERNAL_STORAGE")

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        var binding: Fragment3Binding = DataBindingUtil.inflate(inflater,R.layout.fragment_3,container,false)

        binding.viewModel = viewModel
        binding.listener = this
        binding.rotateListener = this
        binding.scaleListener = this
        binding.lifecycleOwner = this

        return binding.root
    }

    override fun onClick(v: View?) {

        when (v!!.id){

            R.id.bt_chose_img -> onImageChoseBtClick()
            R.id.bt_rotate -> onRotateBtClick()
            R.id.bt_scale -> onScaleBtClick()

        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

        if (requestCode == PICK_IMAGE) {

            if (resultCode == RESULT_OK && data!= null ) {

                var uri : Uri? = data.data

                Glide.with(this)
                        .load(uri)
                        .diskCacheStrategy(DiskCacheStrategy.NONE)
                        .placeholder(R.mipmap.ic_launcher_round).error(R.mipmap.ic_launcher_round)
                        .into(iv_img)

            } else {

                Log.e("Request code:",resultCode.toString())
                Toast.makeText(context,"图片设置失败", Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun onImageChoseBtClick() {

        var intent = Intent(Intent.ACTION_PICK,MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        startActivityForResult(intent, PICK_IMAGE)
        requestPermissions(permissions, PERMISSION_REQUEST_CODE)
    }

    fun onRotateBtClick() {

        var image : Bitmap = BitmapFactory.decodeResource(resources,R.id.iv_img)

        //rotate the image by 90 degrees
        


    }

    fun onScaleBtClick() {

    }


}


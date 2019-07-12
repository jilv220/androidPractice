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
import android.widget.ImageView
import android.widget.Toast
import androidx.core.graphics.scale
import androidx.core.graphics.set
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.myapplication.R
import com.example.myapplication.databinding.Fragment3Binding
import com.example.myapplication.fragmentsViewModel.NotificationFragmentViewModel
import kotlinx.android.synthetic.main.fragment_3.*
import kotlin.math.*

val PICK_IMAGE = 7
val PERMISSION_REQUEST_CODE = 8

class NotificationFragment : Fragment(),View.OnClickListener {

    var viewModel = NotificationFragmentViewModel()
    var permissions : Array<String> = arrayOf(
            "android.permission.READ_EXTERNAL_STORAGE"
            ,"android.permission.WRITE_EXTERNAL_STORAGE")
    var counter: Int = 0
    lateinit var image : Bitmap

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

        if (counter == 0) {
            image = readImgFromImageView(iv_img)
        }

        var degree90 : Double = (90 * PI) / 180
        var rotateConfig : Float = (((counter + 1) * degree90) % 360).toFloat()

        var targetImage = roateImage(image,rotateConfig)
        Log.e("target Image Size", "${targetImage.width} x ${targetImage.height}")

        Glide.with(this)
                .load(targetImage)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .placeholder(R.mipmap.ic_launcher_round)
                .error(R.mipmap.ic_launcher_round)
                .into(iv_img)

        counter++

    }

    fun onScaleBtClick() {

    }

    fun readImgFromImageView(view : ImageView) : Bitmap{

        // enable the cache of the imageView
        view.isDrawingCacheEnabled = true
        var image : Bitmap = view.drawingCache

        Toast.makeText(context,"图片读取完成",Toast.LENGTH_SHORT).show()

        return image
    }

    //

    fun roateImage (image : Bitmap, degree : Float) : Bitmap {

        var width = image.width
        var height = image.height

        // Error
        var errX : Float = 0f
        var errY : Float = 0f

        var fsin = sin(degree)
        var fcos = cos(degree)

        var newHeight : Int = ceil(abs(height * fcos) + abs(width * fsin)).toInt()
        var newWidth : Int = ceil(abs(width * fcos) + abs(height * fsin)).toInt()

        var targetImage :Bitmap = BitmapFactory.decodeResource(resources,R.mipmap.timg).scale(newWidth,newHeight)

        var dstX = (width - newWidth * fcos - newHeight * fsin) / 2
        var dstY = (height + newWidth * fsin - newHeight * fcos) / 2

        for (x in 0..newHeight) {
            for (y in 0..newWidth) {

                var reverseX = x*fcos + y*fsin + dstX
                var reverseY = y*fcos - x*fsin + dstY

                if (reverseX > width - 1 || reverseX < 0 || reverseY > height - 1 || reverseY < 0) {


                } else {

                    targetImage[x,y] = image.getPixel(reverseX.toInt(),reverseY.toInt())
                }

            }
        }

        return targetImage

    }



}


package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.myapplication.WebService.ApiService
import com.example.myapplication.databinding.RetrofitTestActivityBinding
import com.example.myapplication.model.Results
import kotlinx.android.synthetic.main.retrofit_test_activity.*
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val BASE_URL = "https://api.apiopen.top/"

class RetrofitActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.title = "Retrofit Test"

        var binding : RetrofitTestActivityBinding = DataBindingUtil.setContentView(this,R.layout.retrofit_test_activity)
        binding.onClickListener = this
    }

    override fun onClick(v: View?) {

        when (v?.id) {
            R.id.bt_go_back -> {
                val intent = Intent()
                intent.setClass(v.context, FragmentActivity::class.java)
                startActivity(intent)
            }

            R.id.bt_get_json -> {

                val retrofit
                        = Retrofit.Builder()
                        .baseUrl(BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build()

                var apiService : ApiService = retrofit.create(ApiService::class.java)
                var call : Call<Results> = apiService.getResponseBody()

                call.enqueue(object : retrofit2.Callback<Results> {
                    override fun onFailure(call: Call<Results>, t: Throwable) {

                    }

                    override fun onResponse(call: Call<Results>, response: Response<Results>) {
                       var apiData = response.body()

                        tv_retro.text = apiData!!.result[1].text
                    }

                })

            }

        }

    }
}
package com.example.docfriends_android_recruit.home

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.docfriends_android_recruit.R
import com.example.docfriends_android_recruit.api_model.MainDto
import com.example.docfriends_android_recruit.api_model.MainService
import com.example.docfriends_android_recruit.databinding.FragmentHomeBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class HomeFragment: Fragment(R.layout.fragment_home) {

    private var binding: FragmentHomeBinding? = null
    private lateinit var homeAdapter: HomeAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val fragmentHomeBinding = FragmentHomeBinding.bind(view)
        binding = fragmentHomeBinding

        initRecyclerView()

        initApiData()
    }

    private fun initRecyclerView() {
        homeAdapter = HomeAdapter()
        binding?.let {
            it.homeRecyclerView.adapter = homeAdapter
            it.homeRecyclerView.layoutManager = LinearLayoutManager(context)
        }
    }

    private fun initApiData() {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://docfriends.github.io")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        retrofit.create(MainService::class.java).also {
            it.getMainData().enqueue(object: Callback<MainDto> {
                override fun onResponse(call: Call<MainDto>, response: Response<MainDto>) {
                    if (response.isSuccessful.not()) {
                        // 데이터 수신 실패
                        Toast.makeText(context, " 서버 연결에 실패했습니다.\n잠시 후 다시 시도해 주세요", Toast.LENGTH_SHORT)
                        return
                    }
                    response.body()?.let { MainDto ->
                        homeAdapter.submitList(MainDto.consultList)
                        //Log.d("데이터 확인", MainDto.toString())
                    }
                }

                override fun onFailure(call: Call<MainDto>, t: Throwable) {
                    // 데이터 수신 실패
                    Toast.makeText(context, " 서버 연결에 실패했습니다.\n잠시 후 다시 시도해 주세요", Toast.LENGTH_SHORT)
                }

            })
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}
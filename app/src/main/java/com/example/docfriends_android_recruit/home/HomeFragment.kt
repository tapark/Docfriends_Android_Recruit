package com.example.docfriends_android_recruit.home

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.docfriends_android_recruit.R
import com.example.docfriends_android_recruit.main_api_model.ConsultModel
import com.example.docfriends_android_recruit.main_api_model.MainDto
import com.example.docfriends_android_recruit.main_api_model.MainService
import com.example.docfriends_android_recruit.databinding.FragmentHomeBinding
import com.example.docfriends_android_recruit.user_api_model.UserDto
import com.example.docfriends_android_recruit.user_api_model.UserService
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

        initUserApiData()

        initRecyclerView()
    }

    private fun initRecyclerView() {
        context?.let {
            homeAdapter = HomeAdapter(it)
            binding?.let {
                it.homeRecyclerView.adapter = homeAdapter
                it.homeRecyclerView.layoutManager = LinearLayoutManager(context)
            }
        }
    }

    private fun initUserApiData() {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://run.mocky.io")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        retrofit.create(UserService::class.java).also {
            it.getUserData().enqueue(object: Callback<UserDto> {
                override fun onResponse(call: Call<UserDto>, response: Response<UserDto>) {
                    if (response.isSuccessful.not()) {
                        // 데이터 수신 실패
                        Toast.makeText(context, " 서버 연결에 실패했습니다.\n잠시 후 다시 시도해 주세요", Toast.LENGTH_SHORT)
                        return
                    }
                    response.body()?.let { UserDto ->
                        Log.d("User 데이터 확인", UserDto.toString())
                        initMainApiData(UserDto)
                    }
                }

                override fun onFailure(call: Call<UserDto>, t: Throwable) {
                    // 데이터 수신 실패
                    Toast.makeText(context, " 서버 연결에 실패했습니다.\n잠시 후 다시 시도해 주세요", Toast.LENGTH_SHORT)
                }

            })
        }
    }

    private fun initMainApiData(userDto: UserDto) {
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

                        val firstConsultModel = ConsultModel(viewType = TOP_USER_VIEW_TYPE, seq = -1, otherData = userDto)
                        val expertConsultModel = ConsultModel(viewType = EXPERT_VIEW_TYPE, seq = -2, otherData = MainDto.expertList)
                        //val companyConsultModel = ConsultModel(viewType = COMPANY_VIEW_TYPE, seq = -3, otherData = MainDto.companyList)

                        val expertIndex = MainDto.expertListPosition
                        val companyIndex = MainDto.companyListPosition

                        val recyclerViewList = MainDto.consultList.toMutableList<ConsultModel>()
                        recyclerViewList.apply {
                            add(0, firstConsultModel)
                            add(expertIndex, expertConsultModel)
                            //add(companyIndex, companyConsultModel)
                        }
                        homeAdapter.submitList(recyclerViewList)
                        Log.d("Main 데이터 확인", MainDto.toString())
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
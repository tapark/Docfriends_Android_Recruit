package com.example.docfriends_android_recruit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ProgressBar
import android.widget.Toast
import android.widget.Toast.makeText
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.example.docfriends_android_recruit.chat.ChatFragment
import com.example.docfriends_android_recruit.expert.ExpertFragment
import com.example.docfriends_android_recruit.home.COMPANY_VIEW_TYPE
import com.example.docfriends_android_recruit.home.EXPERT_VIEW_TYPE
import com.example.docfriends_android_recruit.home.HomeFragment
import com.example.docfriends_android_recruit.home.TOP_USER_VIEW_TYPE
import com.example.docfriends_android_recruit.main_api_model.ConsultModel
import com.example.docfriends_android_recruit.main_api_model.MainDto
import com.example.docfriends_android_recruit.main_api_model.MainService
import com.example.docfriends_android_recruit.mypage.MyPageFragment
import com.example.docfriends_android_recruit.remote_care.RemoteCareFragment
import com.example.docfriends_android_recruit.user_api_model.UserDto
import com.example.docfriends_android_recruit.user_api_model.UserService
import com.google.android.material.bottomnavigation.BottomNavigationView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    private val homeFragment = HomeFragment()
    private val expertFragment = ExpertFragment()
    private val chatFragment = ChatFragment()
    private val remoteCareFragment = RemoteCareFragment()
    private val myPageFragment = MyPageFragment()

    var recyclerViewList: MutableList<ConsultModel> = mutableListOf()

    private val bottomNavigationView: BottomNavigationView by lazy {
        findViewById<BottomNavigationView>(R.id.bottomNavigationView)
    }

    val progressBar: ProgressBar by lazy {
        findViewById<ProgressBar>(R.id.progressBar)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        progressBar.isVisible = true

        //API 데이터 수신 (UserData -> MainData)
        initApiData()

        // BottomNavigationView 선택된 아이템에 따라 분기
        initBottomNavigationView()

    }

    fun initApiData() {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://run.mocky.io") // User Data
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        retrofit.create(UserService::class.java).also {
            it.getUserData().enqueue(object: Callback<UserDto> {
                override fun onResponse(call: Call<UserDto>, response: Response<UserDto>) {
                    if (response.isSuccessful.not()) {
                        // 데이터 수신 실패
                        apiCommunicationFailMessage()
                        return
                    }
                    response.body()?.let { UserDto ->
                        //Log.d("User 데이터 확인", UserDto.toString())

                        // user data 수신 완료 후 main data 수신
                        initMainApiData(UserDto)
                    }
                }

                override fun onFailure(call: Call<UserDto>, t: Throwable) {
                    // 데이터 수신 실패
                    apiCommunicationFailMessage()
                }

            })
        }
    }

    fun initMainApiData(userDto: UserDto) {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://docfriends.github.io") // Main Data
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        retrofit.create(MainService::class.java).also {
            it.getMainData().enqueue(object: Callback<MainDto> {
                override fun onResponse(call: Call<MainDto>, response: Response<MainDto>) {
                    if (response.isSuccessful.not()) {
                        // 데이터 수신 실패
                        apiCommunicationFailMessage()
                        return
                    }
                    response.body()?.let { MainDto ->

                        val firstConsultModel = ConsultModel(viewType = TOP_USER_VIEW_TYPE, seq = -1, otherData = userDto)
                        val expertConsultModel = ConsultModel(viewType = EXPERT_VIEW_TYPE, seq = -2, otherData = MainDto.expertList)
                        val companyConsultModel = ConsultModel(viewType = COMPANY_VIEW_TYPE, seq = -3, otherData = MainDto.companyList)

                        val expertIndex = MainDto.expertListPosition
                        val companyIndex = MainDto.companyListPosition

                        recyclerViewList = MainDto.consultList.toMutableList<ConsultModel>()
                        recyclerViewList.apply {
                            add(0, firstConsultModel)
                            add(expertIndex, expertConsultModel)
                            add(companyIndex, companyConsultModel)
                        }
                        // API 데이터 수신 완료 후 -> 초기화면 -> homeFragment
                        replaceFragment(homeFragment)
                        // fragment 에서 recyclerview 초기화
                        homeFragment.initRecyclerView()
                        //Log.d("Main 데이터 확인", MainDto.toString())
                    }
                }

                override fun onFailure(call: Call<MainDto>, t: Throwable) {
                    // 데이터 수신 실패
                    apiCommunicationFailMessage()
                }

            })
        }
    }

    private fun apiCommunicationFailMessage() {
        makeText(this.applicationContext, " 서버 연결에 실패했습니다.\n잠시 후 다시 시도해 주세요", Toast.LENGTH_SHORT).show()
    }


    private fun initBottomNavigationView() {
        bottomNavigationView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.home -> replaceFragment(homeFragment)
                R.id.expert -> replaceFragment(expertFragment)
                R.id.chat -> replaceFragment(chatFragment)
                R.id.remoteCare -> replaceFragment(remoteCareFragment)
                R.id.myPage -> replaceFragment(myPageFragment)
            }
            true
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().apply {
                replace(R.id.fragmentContainer, fragment)
                commit()
            }
    }
}
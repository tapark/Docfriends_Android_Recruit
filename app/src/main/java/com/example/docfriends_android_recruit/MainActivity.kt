package com.example.docfriends_android_recruit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.docfriends_android_recruit.chat.ChatFragment
import com.example.docfriends_android_recruit.expert.ExpertFragment
import com.example.docfriends_android_recruit.home.HomeFragment
import com.example.docfriends_android_recruit.mypage.MyPageFragment
import com.example.docfriends_android_recruit.remote_care.RemoteCareFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private val homeFragment = HomeFragment()
    private val expertFragment = ExpertFragment()
    private val chatFragment = ChatFragment()
    private val remoteCareFragment = RemoteCareFragment()
    private val myPageFragment = MyPageFragment()

    private val bottomNavigationView: BottomNavigationView by lazy {
        findViewById<BottomNavigationView>(R.id.bottomNavigationView)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 초기화면 -> homeFragment
        replaceFragment(homeFragment)

        // BottomNavigationView 선택된 아이템에 따라 분기
        initBottomNavigationView()

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
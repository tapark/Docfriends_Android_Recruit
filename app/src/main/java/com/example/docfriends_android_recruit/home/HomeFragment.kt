package com.example.docfriends_android_recruit.home

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.docfriends_android_recruit.MainActivity
import com.example.docfriends_android_recruit.R
import com.example.docfriends_android_recruit.main_api_model.ConsultModel
import com.example.docfriends_android_recruit.databinding.FragmentHomeBinding


class HomeFragment: Fragment(R.layout.fragment_home) {

    private var binding: FragmentHomeBinding? = null
    private lateinit var homeAdapter: HomeAdapter

    private lateinit var recyclerViewList: MutableList<ConsultModel>

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val fragmentHomeBinding = FragmentHomeBinding.bind(view)
        binding = fragmentHomeBinding

        // swipeRefreshLayout color change
        fragmentHomeBinding.swipeRefreshLayout.setColorSchemeColors(resources.getColor(R.color.base_color, null))

        initRecyclerView()

        initSwipeRefreshLayout()
    }

    fun initRecyclerView() {
        context?.let {
            homeAdapter = HomeAdapter(it)
            recyclerViewList = (activity as MainActivity).recyclerViewList
            binding?.let { FragmentHomeBinding ->
                FragmentHomeBinding.homeRecyclerView.adapter = homeAdapter
                FragmentHomeBinding.homeRecyclerView.layoutManager = LinearLayoutManager(context)
                homeAdapter.submitList(recyclerViewList)
                (activity as MainActivity).progressBar.isVisible = false
                FragmentHomeBinding.swipeRefreshLayout.isRefreshing = false
                FragmentHomeBinding.alphaFrameLayout.alpha = 1F
            }
        }
    }

    private fun initSwipeRefreshLayout() {
        binding?.let {
            it.swipeRefreshLayout.setOnRefreshListener {
                it.alphaFrameLayout.alpha = 0.1F
                (activity as MainActivity).initApiData()
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}
package com.example.docfriends_android_recruit.home

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.docfriends_android_recruit.R
import com.example.docfriends_android_recruit.main_api_model.ConsultModel
import com.example.docfriends_android_recruit.databinding.ItemHomeBinding
import com.example.docfriends_android_recruit.databinding.ItemHomeFirstBinding
import com.example.docfriends_android_recruit.user_api_model.UserDto
import com.example.docfriends_android_recruit.user_api_model.UserModel
import java.lang.StringBuilder
import java.text.SimpleDateFormat
import java.util.*

class HomeAdapter(): ListAdapter<ConsultModel, RecyclerView.ViewHolder>(diffUtil) {

    inner class DefaultViewHolder(private val binding: ItemHomeBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(consultModel: ConsultModel) {
            binding.titleTextView.text = consultModel.title
            binding.contextTextView.text = consultModel.context
            if (consultModel.answerCnt > 0) {
                binding.answerTextView.text = "답변 ${consultModel.answerCnt}"
            }
            if (consultModel.tagList.isNotEmpty()) {
                val tagString = StringBuilder()
                consultModel.tagList.forEach {
                    tagString.append("#${it.name} ")
                }
                binding.tagTextView.text = tagString.trim()
            }
            binding.dataTextView.text = SimpleDateFormat("yyyy.MM.dd").format(consultModel.regDate)
        }
    }

    inner class FirstViewHolder(private val binding: ItemHomeFirstBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(consultModel: ConsultModel) {
            val userDto: UserDto = consultModel.otherData as UserDto
            val myAuth = 1
            val myInfo: UserModel? = userDto.users.find {
                it.auth == myAuth
            }
            val seasonSuggestion = when (Calendar.getInstance().get(Calendar.MONTH)) {
                in 3..5 -> userDto.spring
                in 6..8 -> userDto.summer
                in 9..11 -> userDto.fall
                else -> userDto.winter
            }
            if (myInfo == null) {
                binding.userSuggestionTextView.text = "로그인 후 더욱 다양한 서비를 경험하세요!"
            } else {
                binding.userSuggestionTextView.text = "${myInfo.name}님 안녕하세요?\n$seasonSuggestion"
                Glide.with(binding.profileImageView)
                    .load(myInfo.profile)
                    .centerCrop()
                    .into(binding.profileImageView)
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return currentList[position].viewType
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        return when (viewType) {
            0 -> {
                val layoutInflater = LayoutInflater.from(parent.context)
                val view = ItemHomeBinding.inflate(layoutInflater, parent, false)
                DefaultViewHolder(view)
            }
            else -> {
                val layoutInflater = LayoutInflater.from(parent.context)
                val view = ItemHomeFirstBinding.inflate(layoutInflater, parent, false)
                FirstViewHolder(view)
            }
        }

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        when (currentList[position].viewType) {
            0 -> {
                (holder as DefaultViewHolder).bind(currentList[position])
            }
            else -> {
                (holder as FirstViewHolder).bind(currentList[position])
            }
        }
    }

    companion object {
        val diffUtil = object: DiffUtil.ItemCallback<ConsultModel>() {
            override fun areItemsTheSame(oldItem: ConsultModel, newItem: ConsultModel): Boolean {
                return oldItem.seq == newItem.seq
            }

            override fun areContentsTheSame(oldItem: ConsultModel, newItem: ConsultModel): Boolean {
                return oldItem == newItem
            }

        }
    }
}
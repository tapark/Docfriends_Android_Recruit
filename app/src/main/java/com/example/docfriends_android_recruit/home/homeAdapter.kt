package com.example.docfriends_android_recruit.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter

import androidx.recyclerview.widget.RecyclerView
import com.example.docfriends_android_recruit.api_model.ConsultModel
import com.example.docfriends_android_recruit.databinding.ItemHomeBinding
import java.lang.StringBuilder
import java.text.SimpleDateFormat

class HomeAdapter(): ListAdapter<ConsultModel, HomeAdapter.ViewHolder>(diffUtil) {

    inner class ViewHolder(private val binding: ItemHomeBinding): RecyclerView.ViewHolder(binding.root) {
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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = ItemHomeBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(currentList[position])
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
package com.example.docfriends_android_recruit.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.docfriends_android_recruit.databinding.ItemExpertBinding
import com.example.docfriends_android_recruit.main_api_model.ExpertModel
import java.lang.StringBuilder

class ExpertAdapter(): ListAdapter<ExpertModel, ExpertAdapter.ViewHolder>(diffUtil) {

    inner class ViewHolder(private val binding: ItemExpertBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(expertModel: ExpertModel) {
            binding.expertNameTextView.text = "${expertModel.name}(${expertModel.typeName})"
            binding.expertCompanyTextView.text = expertModel.companyName
            if (expertModel.tagList.isNotEmpty()) {
                val tagString = StringBuilder()
                expertModel.tagList.forEach {
                    tagString.append("#${it.name} ")
                }
                binding.expertTagTextView.text = tagString.trim()
            }
            if (expertModel.profileImagePath.isNotEmpty()) {
                Glide.with(binding.expertImageView)
                    .load(expertModel.profileImagePath)
                    .into(binding.expertImageView)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val layoutInflater = LayoutInflater.from(parent.context)
        val view = ItemExpertBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(currentList[position])
    }

    companion object {
        private val diffUtil = object: DiffUtil.ItemCallback<ExpertModel>() {
            override fun areItemsTheSame(oldItem: ExpertModel, newItem: ExpertModel): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: ExpertModel, newItem: ExpertModel): Boolean {
                return oldItem == newItem
            }

        }
    }
}
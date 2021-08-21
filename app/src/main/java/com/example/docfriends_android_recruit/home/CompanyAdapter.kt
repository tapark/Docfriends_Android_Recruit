package com.example.docfriends_android_recruit.home

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.docfriends_android_recruit.databinding.ItemCompanyBinding
import com.example.docfriends_android_recruit.main_api_model.CompanyModel

class CompanyAdapter: ListAdapter<CompanyModel, CompanyAdapter.ViewHolder>(diffUtil) {

    inner class ViewHolder(private val binding: ItemCompanyBinding): RecyclerView.ViewHolder(binding.root) {

        @SuppressLint("SetTextI18n")
        fun bind(companyModel: CompanyModel) {
            binding.companyNameTextView.text = companyModel.companyName
            binding.companyAddressTextView.text = "${companyModel.address} ${companyModel.addressEtc}"
            if (companyModel.introPath.isNotEmpty()) {
                Glide.with(binding.companyImageView)
                    .load(companyModel.introPath)
                    .into(binding.companyImageView)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val layoutInflater = LayoutInflater.from(parent.context)
        val view = ItemCompanyBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(currentList[position])
    }

    companion object {
        private val diffUtil = object: DiffUtil.ItemCallback<CompanyModel>() {

            override fun areItemsTheSame(oldItem: CompanyModel, newItem: CompanyModel): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: CompanyModel, newItem: CompanyModel): Boolean {
                return oldItem == newItem
            }

        }
    }
}
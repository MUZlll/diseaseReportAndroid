package com.muz1i.diseasereportandroid.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.muz1i.diseasereportandroid.R
import com.muz1i.diseasereportandroid.bean.UserInfoData
import com.muz1i.diseasereportandroid.databinding.ItemUserInfoBinding
import com.muz1i.diseasereportandroid.databinding.ItemUserInfoHeaderBinding

/**
 * @author: Muz1i
 * @date: 2021/4/29
 */
class UserInfoAdapter : RecyclerView.Adapter<UserInfoAdapter.InnerViewHolder>() {

    val userList by lazy {
        val list = ArrayList<UserInfoData>()
        //添加一个空UserInfoData用来占位，为了添加userInfoHeader
        list.add(
            UserInfoData(
                0,
                "0",
                "0",
                "0",
                "0",
                "0",
                "0",
                "0",
                "0",
                "0"
            )
        )
        list
    }

    class InnerViewHolder(itemBinding: ViewDataBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
        val binding = itemBinding
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InnerViewHolder {
        return if (viewType == 0) {
            val binding = DataBindingUtil.inflate<ItemUserInfoHeaderBinding>(
                LayoutInflater.from(parent.context),
                R.layout.item_user_info_header,
                parent,
                false
            )
            InnerViewHolder(binding)
        } else {
            val binding = DataBindingUtil.inflate<ItemUserInfoBinding>(
                LayoutInflater.from(parent.context),
                R.layout.item_user_info, parent, false
            )
            InnerViewHolder(binding)
        }
    }

    override fun onBindViewHolder(holder: InnerViewHolder, position: Int) {
        holder.binding.run {
            if (this is ItemUserInfoBinding) {
                this.viewModel = userList[position]
                this.executePendingBindings()
            }
        }

    }

    override fun getItemCount(): Int {
        return userList.size
    }

    override fun getItemViewType(position: Int): Int {
        return if (position == 0) 0 else 1
    }
}

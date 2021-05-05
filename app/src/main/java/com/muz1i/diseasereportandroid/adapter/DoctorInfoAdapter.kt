package com.muz1i.diseasereportandroid.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.muz1i.diseasereportandroid.R
import com.muz1i.diseasereportandroid.bean.DoctorInfoData
import com.muz1i.diseasereportandroid.databinding.ItemDoctorInfoBinding
import com.muz1i.diseasereportandroid.databinding.ItemDoctorInfoHeaderBinding

/**
 * @author: Muz1i
 * @date: 2021/5/4
 */
class DoctorInfoAdapter : RecyclerView.Adapter<DoctorInfoAdapter.InnerViewHolder>() {

    private lateinit var onItemClickListener: OnItemClickListener

    private val doctorList by lazy {
        ArrayList<DoctorInfoData>()
    }

    class InnerViewHolder(itemBinding: ViewDataBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
        val binding = itemBinding
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InnerViewHolder {
        return if (viewType == 0) {
            val binding = DataBindingUtil.inflate<ItemDoctorInfoHeaderBinding>(
                LayoutInflater.from(parent.context),
                R.layout.item_doctor_info_header,
                parent,
                false
            )
            InnerViewHolder(binding)
        } else {
            val binding = DataBindingUtil.inflate<ItemDoctorInfoBinding>(
                LayoutInflater.from(parent.context),
                R.layout.item_doctor_info, parent,
                false
            )
            InnerViewHolder(binding)
        }
    }

    override fun onBindViewHolder(holder: InnerViewHolder, position: Int) {
        holder.binding.run {
            if (this is ItemDoctorInfoBinding) {
                viewModel = doctorList[position]
                executePendingBindings()
                root.setOnClickListener {
                    onItemClickListener.onItemClick(it, position, doctorList[position].id!!)
                }
                root.setOnLongClickListener {
                    onItemClickListener.onItemLongClick(it, position, doctorList[position].id!!)
                    true
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return doctorList.size
    }

    override fun getItemViewType(position: Int): Int {
        return if (position == 0) 0 else 1
    }

    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.onItemClickListener = listener
    }

    interface OnItemClickListener {
        fun onItemClick(view: View, position: Int, id: Int)
        fun onItemLongClick(view: View, position: Int, id: Int)
    }

    fun setData(list: List<DoctorInfoData>) {
        doctorList.clear()
        //添加一个空UserInfoData用来占位，为了添加userInfoHeader
        doctorList.add(
            DoctorInfoData(
                0,
                "0",
                "0",
                "0"
            )
        )
        doctorList.addAll(list)
        notifyDataSetChanged()
    }

    fun addData(list: List<DoctorInfoData>) {
        val oldSize = doctorList.size
        doctorList.addAll(list)
        notifyItemRangeChanged(oldSize, doctorList.size)
    }

    fun removeItem(pos: Int) {
        doctorList.removeAt(pos)
        notifyItemRemoved(pos)
    }
}

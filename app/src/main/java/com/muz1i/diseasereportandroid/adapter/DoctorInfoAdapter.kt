package com.muz1i.diseasereportandroid.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.muz1i.diseasereportandroid.R
import com.muz1i.diseasereportandroid.bean.DoctorInfoData
import com.muz1i.diseasereportandroid.databinding.ItemDoctorInfoBinding

/**
 * @author: Muz1i
 * @date: 2021/5/4
 */
class DoctorInfoAdapter : RecyclerView.Adapter<DoctorInfoAdapter.InnerViewHolder>() {

    private lateinit var onItemClickListener: OnItemClickListener

    private val doctorList by lazy {
        ArrayList<DoctorInfoData>()
    }

    class InnerViewHolder(itemBinding: ItemDoctorInfoBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
        val binding = itemBinding
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InnerViewHolder {
        val binding = DataBindingUtil.inflate<ItemDoctorInfoBinding>(
            LayoutInflater.from(parent.context),
            R.layout.item_doctor_info, parent,
            false
        )
        return InnerViewHolder(binding)
    }

    override fun onBindViewHolder(holder: InnerViewHolder, position: Int) {
        holder.binding.run {
            val doctorInfoData = doctorList[position]
            viewModel = doctorInfoData
            imageSex.setImageResource(if (doctorInfoData.sex == "男") R.mipmap.man else R.mipmap.woman)
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

    override fun getItemCount(): Int {
        return doctorList.size
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

package com.muz1i.diseasereportandroid.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.view.get
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.internal.LinkedTreeMap
import com.muz1i.diseasereportandroid.R
import com.muz1i.diseasereportandroid.bean.PunchData
import com.muz1i.diseasereportandroid.databinding.ItemTaskDetailBinding

/**
 * @author: Muz1i
 * @date: 2021/5/12
 */
class TaskDetailAdapter : RecyclerView.Adapter<TaskDetailAdapter.InnerViewHolder>() {
    private val headerList by lazy {
        mutableListOf<Any>()
    }

    private val punchDataList by lazy {
        mutableListOf<Any>()
    }

    class InnerViewHolder(itemBinding: ItemTaskDetailBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
        val binding = itemBinding
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InnerViewHolder {
        val binding = DataBindingUtil.inflate<ItemTaskDetailBinding>(
            LayoutInflater.from(parent.context),
            R.layout.item_task_detail,
            parent,
            false
        )
        return InnerViewHolder(binding)
    }

    override fun onBindViewHolder(holder: InnerViewHolder, position: Int) {
        val punchData = punchDataList[position] as LinkedTreeMap<*, *>
        val id = punchData[headerList[0]].toString()
        (holder.binding.contentDefault[0] as TextView).text = id.substringBefore(".")
        val date = punchData[headerList[1]].toString().substring(0, 10)
        (holder.binding.contentDefault[1] as TextView).text = date
        val userId = punchData[headerList.last()].toString()
        (holder.binding.contentDefault[2] as TextView).text = userId
        for (i in 2 until headerList.size - 1) {
            val contentItem = punchData[headerList[i]].toString()
            (holder.binding.contentContainer[i - 2] as TextView).text = contentItem
        }
    }

    override fun getItemCount(): Int {
        return punchDataList.size
    }

    fun setData(it: PunchData) {
        punchDataList.clear()
        headerList.clear()
        punchDataList.addAll(it.dataList)
        headerList.addAll(it.columns)
        notifyDataSetChanged()
    }
}

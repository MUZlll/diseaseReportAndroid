package com.muz1i.diseasereportandroid.adapter

import android.animation.ValueAnimator
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.muz1i.diseasereportandroid.R
import com.muz1i.diseasereportandroid.bean.DiseaseCaseData
import com.muz1i.diseasereportandroid.databinding.ItemDiseaseCaseBinding
import com.muz1i.diseasereportandroid.utils.SizeUtils

/**
 * @author: Muz1i
 * @date: 2021/5/6
 */
class DiseaseCaseAdapter : RecyclerView.Adapter<DiseaseCaseAdapter.InnerViewHolder>() {

    private lateinit var onItemClickListener: OnItemClickListener
    private val diseaseCaseList by lazy {
        ArrayList<DiseaseCaseData>()
    }

    class InnerViewHolder(val binding: ItemDiseaseCaseBinding) :
        RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InnerViewHolder {
        val binding = DataBindingUtil.inflate<ItemDiseaseCaseBinding>(
            LayoutInflater.from(parent.context),
            R.layout.item_disease_case,
            parent,
            false
        )
        return InnerViewHolder(binding)
    }

    override fun onBindViewHolder(holder: InnerViewHolder, position: Int) {
        holder.binding.run {
            val diseaseCaseData = diseaseCaseList[position]
            viewModel = diseaseCaseData
            when (diseaseCaseData.diseaseLevel) {
                "甲" -> diseaseLevel.setBackgroundResource(R.drawable.shape_disease_case_level_red_bg)
                "乙" -> diseaseLevel.setBackgroundResource(R.drawable.shape_disease_case_level_yellow_bg)
                "丙" -> diseaseLevel.setBackgroundResource(R.drawable.shape_disease_case_level_blue_bg)
            }
            executePendingBindings()
            root.setOnClickListener {
                onItemClickListener.onItemClick(it, position, diseaseCaseData)
            }
            root.setOnLongClickListener {
                onItemClickListener.onItemLongClick(it, position, diseaseCaseData.id)
                false
            }
            row.setOnClickListener {
                displayHintLayout(closeLayout, it)
            }
        }
    }

    private fun displayHintLayout(closeLayout: View, row: View) {
        val height = SizeUtils.dip2px(40f)
        val va: ValueAnimator = if (closeLayout.height == 0) {
            ValueAnimator.ofInt(0, height)
        } else {
            ValueAnimator.ofInt(height, 0)
        }
        va.addUpdateListener {
            closeLayout.layoutParams.height = it.animatedValue as Int
            closeLayout.requestLayout()
        }
        va.duration = 200
        va.start()
        row.rotationX += 180f
    }

    override fun getItemCount(): Int {
        return diseaseCaseList.size
    }

    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.onItemClickListener = listener
    }

    interface OnItemClickListener {
        fun onItemClick(view: View, position: Int, diseaseCaseData: DiseaseCaseData)
        fun onItemLongClick(view: View, position: Int, id: Int)
    }

    fun setData(list: List<DiseaseCaseData>) {
        diseaseCaseList.clear()
        diseaseCaseList.addAll(list)
        notifyDataSetChanged()
    }

    fun addData(list: List<DiseaseCaseData>) {
        val oldSize = diseaseCaseList.size
        diseaseCaseList.addAll(list)
        notifyItemRangeChanged(oldSize, diseaseCaseList.size)
    }

    fun removeItem(pos: Int) {
        diseaseCaseList.removeAt(pos)
        notifyItemRemoved(pos)
    }
}

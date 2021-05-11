package com.muz1i.diseasereportandroid.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.muz1i.diseasereportandroid.R
import com.muz1i.diseasereportandroid.bean.PunchTableData
import com.muz1i.diseasereportandroid.databinding.ItemPunchListBinding

/**
 * @author: Muz1i
 * @date: 2021/5/11
 */
class PunchListAdapter : RecyclerView.Adapter<PunchListAdapter.InnerViewHolder>() {

    private lateinit var onItemClickListener: OnItemClickListener

    private val punchTableList by lazy {
        ArrayList<PunchTableData>()
    }

    class InnerViewHolder(itemBinding: ItemPunchListBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
        val binding = itemBinding
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InnerViewHolder {
        val itemBinding = DataBindingUtil.inflate<ItemPunchListBinding>(
            LayoutInflater.from(parent.context),
            R.layout.item_punch_list,
            parent,
            false
        )
        return InnerViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: InnerViewHolder, position: Int) {
        holder.binding.run {
            val punchTableData = punchTableList[position]
            viewModel = punchTableData
            executePendingBindings()
            root.setOnClickListener {
                onItemClickListener.onItemClick(it, position, punchTableData.name)
            }
            root.setOnLongClickListener {
                onItemClickListener.onItemLongClick(it, position, punchTableData.name)
                true
            }
        }
    }

    override fun getItemCount(): Int {
        return punchTableList.size
    }

    interface OnItemClickListener {
        fun onItemClick(view: View, position: Int, tableName: String)
        fun onItemLongClick(view: View, position: Int, tableName: String)
    }

    fun setData(list: List<PunchTableData>) {
        punchTableList.clear()
        punchTableList.addAll(list)
        notifyDataSetChanged()
    }

    fun addData(list: List<PunchTableData>) {
        val oldSize = punchTableList.size
        punchTableList.addAll(list)
        notifyItemRangeChanged(oldSize, punchTableList.size)
    }

    fun removeItem(pos: Int) {
        punchTableList.removeAt(pos)
        notifyItemRemoved(pos)
    }
}

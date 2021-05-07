package com.muz1i.diseasereportandroid.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.muz1i.diseasereportandroid.R
import com.muz1i.diseasereportandroid.bean.UserInfoData
import com.muz1i.diseasereportandroid.databinding.ItemUserInfoBinding

/**
 * @author: Muz1i
 * @date: 2021/4/29
 */
class UserInfoAdapter : RecyclerView.Adapter<UserInfoAdapter.InnerViewHolder>() {

    private lateinit var onItemClickListener: OnItemClickListener
    private val userList by lazy {
        ArrayList<UserInfoData>()
    }

    class InnerViewHolder(itemBinding: ItemUserInfoBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
        val binding = itemBinding
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InnerViewHolder {
        val binding = DataBindingUtil.inflate<ItemUserInfoBinding>(
            LayoutInflater.from(parent.context),
            R.layout.item_user_info, parent, false
        )
        return InnerViewHolder(binding)
    }

    override fun onBindViewHolder(holder: InnerViewHolder, position: Int) {
        holder.binding.run {
            val userInfoData = userList[position]
            viewModel = userInfoData
            imageSex.setImageResource(if (userInfoData.sex == "男") R.mipmap.man else R.mipmap.woman)
            executePendingBindings()
            root.setOnClickListener {
                onItemClickListener.onItemClick(it, position, userList[position].studentNum)
            }
            root.setOnLongClickListener {
                onItemClickListener.onItemLongClick(
                    it,
                    position,
                    userList[position].id!!
                )
                true
            }
        }
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.onItemClickListener = listener
    }

    interface OnItemClickListener {
        fun onItemClick(view: View, position: Int, stuNum: String)
        fun onItemLongClick(view: View, position: Int, id: Int)
    }

    fun setData(list: List<UserInfoData>) {
        userList.clear()
        userList.addAll(list)
        notifyDataSetChanged()
    }

    fun addData(list: List<UserInfoData>) {
        val oldSize = userList.size
        userList.addAll(list)
        notifyItemRangeChanged(oldSize, userList.size)
    }

    fun removeItem(pos: Int) {
        userList.removeAt(pos)
        notifyItemRemoved(pos)
    }
}

package com.muz1i.diseasereportandroid.view.fragment.manage

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.muz1i.diseasereportandroid.R
import com.muz1i.diseasereportandroid.adapter.UserInfoAdapter
import com.muz1i.diseasereportandroid.base.BaseFragment
import com.muz1i.diseasereportandroid.databinding.FragmentUserMangeBinding
import com.muz1i.diseasereportandroid.utils.LoadState
import com.muz1i.diseasereportandroid.utils.SizeUtils
import com.muz1i.diseasereportandroid.viewmodel.manage.UserManageViewModel

/**
 * @author: Muz1i
 * @date: 2021/4/29
 */
class UserManageFragment : BaseFragment<UserManageViewModel, FragmentUserMangeBinding>() {
    private val userInfoAdapter by lazy {
        UserInfoAdapter()
    }

    override fun getVMClass(): Class<UserManageViewModel> {
        return UserManageViewModel::class.java
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_user_mange
    }

    override fun initView() {
        binding.userRv.run {
            layoutManager = LinearLayoutManager(context)
            adapter = userInfoAdapter
            addItemDecoration(object : RecyclerView.ItemDecoration() {
                override fun getItemOffsets(
                    outRect: Rect,
                    view: View,
                    parent: RecyclerView,
                    state: RecyclerView.State
                ) {
                    outRect.top = SizeUtils.dip2px(context, 2.5f)
                    outRect.bottom = SizeUtils.dip2px(context, 2.5f)
                    outRect.left = SizeUtils.dip2px(context, 2.5f)
                    outRect.right = SizeUtils.dip2px(context, 2.5f)
                }
            })
        }
    }

    override fun loadData() {
        viewModel.loadState.value = LoadState.LOADING
        viewModel.getUserList(1, 10)
        viewModel.getUserList(2, 10)
    }

    override fun observeData() {
        viewModel.userList.observe(this, {
            userInfoAdapter.run {
                userList.addAll(it)
                notifyDataSetChanged()
            }
        })
    }
}

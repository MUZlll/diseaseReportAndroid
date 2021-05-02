package com.muz1i.diseasereportandroid.view.fragment.manage

import android.content.Intent
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.muz1i.diseasereportandroid.R
import com.muz1i.diseasereportandroid.adapter.UserInfoAdapter
import com.muz1i.diseasereportandroid.base.BaseFragment
import com.muz1i.diseasereportandroid.bean.UserInfoData
import com.muz1i.diseasereportandroid.databinding.FragmentUserMangeBinding
import com.muz1i.diseasereportandroid.utils.Constants
import com.muz1i.diseasereportandroid.utils.LoadState
import com.muz1i.diseasereportandroid.view.activity.UserDetailActivity
import com.muz1i.diseasereportandroid.viewmodel.manage.UserViewModel

/**
 * @author: Muz1i
 * @date: 2021/4/29
 */
class UserManageFragment : BaseFragment<UserViewModel, FragmentUserMangeBinding>() {
    private val userInfoAdapter by lazy {
        UserInfoAdapter()
    }

    private var currentPage = 1
    private val list by lazy {
        ArrayList<UserInfoData>()
    }

    override fun getVMClass(): Class<UserViewModel> {
        return UserViewModel::class.java
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_user_mange
    }

    override fun initView() {
        binding.userRv.run {
            layoutManager = LinearLayoutManager(context)
            adapter = userInfoAdapter
        }
    }

    override fun initEvent() {
        userInfoAdapter.setOnItemClickListener(object : UserInfoAdapter.OnItemClickListener {
            override fun onItemClick(view: View, position: Int, stuNum: String) {
                val intent = Intent(context, UserDetailActivity::class.java)
                intent.putExtra(Constants.STU_NUM, stuNum)
                startActivity(intent)
            }
        })
    }

    override fun loadData() {
        viewModel.loadState.value = LoadState.LOADING
        viewModel.getUserList(currentPage, 10)
    }

    private fun loadMore() {
        currentPage++
        viewModel.getUserList(currentPage, 10)
    }

    override fun observeData() {
        viewModel.userList.observe(this, {
            if (currentPage == 1) {
                userInfoAdapter.setData(it)
            } else {
                userInfoAdapter.addData(it)
            }
        })
    }

    override fun onResume() {
        loadData()
        super.onResume()
    }
}

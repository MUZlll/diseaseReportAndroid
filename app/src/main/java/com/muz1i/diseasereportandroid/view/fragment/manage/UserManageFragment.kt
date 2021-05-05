package com.muz1i.diseasereportandroid.view.fragment.manage

import android.app.AlertDialog
import android.content.Intent
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.lcodecore.tkrefreshlayout.RefreshListenerAdapter
import com.lcodecore.tkrefreshlayout.TwinklingRefreshLayout
import com.muz1i.diseasereportandroid.R
import com.muz1i.diseasereportandroid.adapter.UserInfoAdapter
import com.muz1i.diseasereportandroid.base.BaseFragment
import com.muz1i.diseasereportandroid.databinding.FragmentUserMangeBinding
import com.muz1i.diseasereportandroid.utils.Constants
import com.muz1i.diseasereportandroid.utils.LoadState
import com.muz1i.diseasereportandroid.utils.ToastUtils
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

    private var deletePos = 999
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
                intent.putExtra(Constants.IS_ADD_BUTTON_CLICK, false)
                intent.putExtra(Constants.STU_NUM, stuNum)
                startActivity(intent)
            }

            override fun onItemLongClick(view: View, position: Int, id: Int) {
                AlertDialog.Builder(context).apply {
                    setMessage("删除用户")
                    setCancelable(false)
                    setPositiveButton("确认") { _, _ ->
                        viewModel.deleteUser(id)
                        deletePos = position
                    }
                    setNegativeButton("取消") { _, _ -> }
                    show()
                }
            }
        })

        binding.userManageRefresh.setOnRefreshListener(object : RefreshListenerAdapter() {
            override fun onLoadMore(refreshLayout: TwinklingRefreshLayout?) {
                loadMore()
            }

            override fun onRefresh(refreshLayout: TwinklingRefreshLayout?) {
                loadData()
            }
        })
    }

    override fun loadData() {
        currentPage = 1
        viewModel.loadState.value = LoadState.LOADING
        viewModel.getUserList(currentPage, Constants.PAGE_SIZE)
    }

    override fun observeData() {
        viewModel.run {
            userList.observe(this@UserManageFragment, {
                if (currentPage == 1) {
                    userInfoAdapter.setData(it)
                    binding.userManageRefresh.finishRefreshing()
                } else {
                    if (it.isNotEmpty()) {
                        userInfoAdapter.addData(it)
                        ToastUtils.showToast("加载了${it.size}条数据")
                    } else {
                        ToastUtils.showToast("没有更多数据")
                    }
                    binding.userManageRefresh.finishLoadmore()
                }
            })
            deleteSuccess.observe(this@UserManageFragment, {
                if (it) {
                    userInfoAdapter.removeItem(deletePos)
                    ToastUtils.showToast("删除成功")
                } else {
                    ToastUtils.showToast("删除失败，请稍后重试")
                }
            })
        }
    }

    private fun loadMore() {
        currentPage++
        viewModel.getUserList(currentPage, Constants.PAGE_SIZE)
    }

    override fun reload() {
        loadData()
    }

    override fun onResume() {
        val userManageContainerFragment = parentFragment as UserManageContainerFragment
        userManageContainerFragment.setOnAddBtnClickListener {
            val intent = Intent(context, UserDetailActivity::class.java)
            intent.putExtra(Constants.IS_ADD_BUTTON_CLICK, true)
            startActivity(intent)
        }
        super.onResume()
    }
}

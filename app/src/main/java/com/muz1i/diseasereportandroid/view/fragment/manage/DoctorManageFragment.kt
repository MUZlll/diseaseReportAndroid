package com.muz1i.diseasereportandroid.view.fragment.manage

import android.app.AlertDialog
import android.content.Intent
import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.lcodecore.tkrefreshlayout.RefreshListenerAdapter
import com.lcodecore.tkrefreshlayout.TwinklingRefreshLayout
import com.muz1i.diseasereportandroid.R
import com.muz1i.diseasereportandroid.adapter.DoctorInfoAdapter
import com.muz1i.diseasereportandroid.base.BaseFragment
import com.muz1i.diseasereportandroid.databinding.FragmentDoctorMangeBinding
import com.muz1i.diseasereportandroid.utils.Constants
import com.muz1i.diseasereportandroid.utils.LoadState
import com.muz1i.diseasereportandroid.utils.SizeUtils
import com.muz1i.diseasereportandroid.utils.ToastUtils
import com.muz1i.diseasereportandroid.view.activity.DoctorDetailActivity
import com.muz1i.diseasereportandroid.viewmodel.manage.DoctorViewModel

/**
 * @author: Muz1i
 * @date: 2021/4/29
 */
class DoctorManageFragment : BaseFragment<DoctorViewModel, FragmentDoctorMangeBinding>() {

    private var currentPage = 1

    private var deletePos = 999

    private val doctorInfoAdapter by lazy {
        DoctorInfoAdapter()
    }

    override fun getVMClass(): Class<DoctorViewModel> {
        return DoctorViewModel::class.java
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_doctor_mange
    }

    override fun initView() {
        binding.doctorRv.run {
            layoutManager = LinearLayoutManager(context)
            adapter = doctorInfoAdapter
            addItemDecoration(object : RecyclerView.ItemDecoration() {
                override fun getItemOffsets(
                    outRect: Rect,
                    view: View,
                    parent: RecyclerView,
                    state: RecyclerView.State
                ) {
                    outRect.top = SizeUtils.dip2px(2.5f)
                    outRect.bottom = SizeUtils.dip2px(2.5f)
                }
            })
        }
    }

    override fun initEvent() {
        doctorInfoAdapter.setOnItemClickListener(object : DoctorInfoAdapter.OnItemClickListener {
            override fun onItemClick(view: View, position: Int, id: Int) {
                val intent = Intent(context, DoctorDetailActivity::class.java)
                intent.putExtra(Constants.IS_ADD_BUTTON_CLICK, false)
                intent.putExtra(Constants.DOCTOR_ID, id)
                startActivity(intent)
            }

            override fun onItemLongClick(view: View, position: Int, id: Int) {
                AlertDialog.Builder(context).apply {
                    setMessage("删除用户")
                    setCancelable(false)
                    setPositiveButton("确认") { _, _ ->
                        viewModel.deleteDoctor(id)
                        deletePos = position
                    }
                    setNegativeButton("取消") { _, _ -> }
                    show()
                }
            }

        })
        binding.doctorManageRefresh.setOnRefreshListener(object : RefreshListenerAdapter() {
            override fun onRefresh(refreshLayout: TwinklingRefreshLayout?) {
                loadData()
            }

            override fun onLoadMore(refreshLayout: TwinklingRefreshLayout?) {
                loadMore()
            }
        })

    }

    override fun loadData() {
        currentPage = 1
        viewModel.loadState.value = LoadState.LOADING
        viewModel.getDoctorList(currentPage, Constants.PAGE_SIZE)
    }

    override fun observeData() {
        viewModel.run {
            doctorList.observe(this@DoctorManageFragment, {
                if (currentPage == 1) {
                    if (it.isNotEmpty()) {
                        doctorInfoAdapter.setData(it)
                        loadState.value = LoadState.SUCCESS
                    } else {
                        loadState.value = LoadState.EMPTY
                    }
                    loadState.value = LoadState.SUCCESS
                    binding.doctorManageRefresh.finishRefreshing()
                } else {
                    if (it.isNotEmpty()) {
                        doctorInfoAdapter.addData(it)
                        ToastUtils.showToast("加载了${it.size}条数据")
                    } else {
                        ToastUtils.showToast("没有更多数据")
                    }
                    binding.doctorManageRefresh.finishLoadmore()
                }
            })
            deleteSuccess.observe(this@DoctorManageFragment, {
                if (it) {
                    doctorInfoAdapter.removeItem(deletePos)
                    ToastUtils.showToast("删除成功")
                } else {
                    ToastUtils.showToast("删除失败，请稍后重试")
                }
            })
        }
    }

    private fun loadMore() {
        currentPage++
        viewModel.getDoctorList(currentPage, Constants.PAGE_SIZE)
    }

    override fun reload() {
        loadData()
    }

    override fun onResume() {
        val userManageContainerFragment = parentFragment as UserManageContainerFragment
        userManageContainerFragment.setOnAddBtnClickListener {
            val intent = Intent(context, DoctorDetailActivity::class.java)
            intent.putExtra(Constants.IS_ADD_BUTTON_CLICK, true)
            startActivity(intent)
        }
        super.onResume()
    }
}

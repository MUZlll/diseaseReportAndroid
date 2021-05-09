package com.muz1i.diseasereportandroid.view.fragment.diseasecase

import android.app.AlertDialog
import android.content.Intent
import android.graphics.Rect
import android.view.View
import android.widget.AutoCompleteTextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.lcodecore.tkrefreshlayout.RefreshListenerAdapter
import com.lcodecore.tkrefreshlayout.TwinklingRefreshLayout
import com.muz1i.diseasereportandroid.R
import com.muz1i.diseasereportandroid.adapter.DiseaseCaseAdapter
import com.muz1i.diseasereportandroid.adapter.MyArrayAdapter
import com.muz1i.diseasereportandroid.base.BaseApplication
import com.muz1i.diseasereportandroid.base.BaseFragment
import com.muz1i.diseasereportandroid.bean.DiseaseCaseFilterData
import com.muz1i.diseasereportandroid.databinding.FragmentDiseaseCaseBinding
import com.muz1i.diseasereportandroid.utils.*
import com.muz1i.diseasereportandroid.view.activity.DiseaseCaseAddActivity
import com.muz1i.diseasereportandroid.view.activity.DiseaseCaseDetailActivity
import com.muz1i.diseasereportandroid.viewmodel.diseasecase.DiseaseCaseViewModel

/**
 * @author: Muz1i
 * @date: 2021/4/25
 */
class DiseaseCaseFragment : BaseFragment<DiseaseCaseViewModel, FragmentDiseaseCaseBinding>() {

    private var currentPage = 1
    private var deletePos = -1

    private val diseaseCaseAdapter by lazy {
        DiseaseCaseAdapter()
    }

    override fun getVMClass(): Class<DiseaseCaseViewModel> {
        return DiseaseCaseViewModel::class.java
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_disease_case
    }

    override fun initView() {
        binding.diseaseCaseRv.run {
            adapter = diseaseCaseAdapter
            layoutManager = LinearLayoutManager(context)
            addItemDecoration(object : RecyclerView.ItemDecoration() {
                override fun getItemOffsets(
                    outRect: Rect,
                    view: View,
                    parent: RecyclerView,
                    state: RecyclerView.State
                ) {
                    outRect.top = SizeUtils.dip2px(2.5f)
                    outRect.bottom = SizeUtils.dip2px(2.5f)
                    outRect.left = SizeUtils.dip2px(2.5f)
                    outRect.right = SizeUtils.dip2px(2.5f)
                }
            })
        }
        binding.run {
            (filterLevel.editText as AutoCompleteTextView).setAdapter(
                MyArrayAdapter(
                    BaseApplication.appContext,
                    R.layout.list_item,
                    Constants.LEVEL_ITEMS
                )
            )
            (filterInstitute.editText as AutoCompleteTextView).setAdapter(
                MyArrayAdapter(
                    BaseApplication.appContext,
                    R.layout.list_item,
                    Constants.INSTITUTE_ITEMS
                )
            )
            filterViewModel = DiseaseCaseFilterData("", "")
        }
    }

    override fun initEvent() {
        diseaseCaseAdapter.setOnItemClickListener(object : DiseaseCaseAdapter.OnItemClickListener {
            override fun onItemClick(view: View, position: Int, id: Int) {
                val intent = Intent(context, DiseaseCaseDetailActivity::class.java)
                intent.putExtra(Constants.DISEASE_CASE_ID, id)
                startActivity(intent)
            }

            override fun onItemLongClick(view: View, position: Int, id: Int) {
                AlertDialog.Builder(context).apply {
                    setMessage("删除用户")
                    setCancelable(false)
                    setPositiveButton("确认") { _, _ ->
                        viewModel.deleteDiseaseCase(id)
                        deletePos = position
                    }
                    setNegativeButton("取消") { _, _ -> }
                    show()
                }
            }
        })
        binding.diseaseCaseRefresh.setOnRefreshListener(object : RefreshListenerAdapter() {
            override fun onRefresh(refreshLayout: TwinklingRefreshLayout?) {
                loadData()
            }

            override fun onLoadMore(refreshLayout: TwinklingRefreshLayout?) {
                loadMore()
            }
        })
        binding.addButton.setOnClickListener {
            val intent = Intent(context, DiseaseCaseAddActivity::class.java)
            startActivity(intent)
        }
    }

    override fun loadData() {
        currentPage = 1
        viewModel.loadState.value = LoadState.LOADING
        binding.filterViewModel?.run {
            viewModel.getDiseaseCaseList(
                filterLevel,
                filterInstitute, currentPage,
                Constants.PAGE_SIZE
            )
        }
    }

    private fun loadMore() {
        currentPage++
        binding.filterViewModel?.run {
            viewModel.getDiseaseCaseList(
                filterLevel,
                filterInstitute, currentPage,
                Constants.PAGE_SIZE
            )
        }
    }

    override fun reload() {
        loadData()
    }

    override fun observeData() {
        viewModel.run {
            diseaseCaseList.observe(this@DiseaseCaseFragment, {
                if (currentPage == 1) {
                    if (it.isNotEmpty()) {
                        diseaseCaseAdapter.setData(it)
                        loadState.value = LoadState.SUCCESS
                    } else {
                        loadState.value = LoadState.EMPTY
                    }
                    binding.diseaseCaseRefresh.finishRefreshing()
                } else {
                    if (it.isNotEmpty()) {
                        diseaseCaseAdapter.addData(it)
                        ToastUtils.showToast("加载了${it.size}条数据")
                    } else {
                        ToastUtils.showToast("没有更多数据")
                    }
                    binding.diseaseCaseRefresh.finishLoadmore()
                }
            })
            deleteSuccess.observe(this@DiseaseCaseFragment, {
                if (it) {
                    diseaseCaseAdapter.removeItem(deletePos)
                    ToastUtils.showToast("删除成功")
                } else {
                    ToastUtils.showToast("删除失败，请稍后重试")
                }
            })
        }
    }
}

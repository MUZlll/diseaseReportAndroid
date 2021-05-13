package com.muz1i.diseasereportandroid.view.activity

import android.graphics.Rect
import android.view.View
import android.widget.TextView
import androidx.core.view.get
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.muz1i.diseasereportandroid.R
import com.muz1i.diseasereportandroid.adapter.TaskDetailAdapter
import com.muz1i.diseasereportandroid.base.BaseActivity
import com.muz1i.diseasereportandroid.databinding.ActivityTaskDetailBinding
import com.muz1i.diseasereportandroid.utils.Constants
import com.muz1i.diseasereportandroid.utils.SizeUtils
import com.muz1i.diseasereportandroid.viewmodel.punch.PunchViewModel

/**
 * @author: Muz1i
 * @date: 2021/5/12
 */
class TaskDetailActivity : BaseActivity<PunchViewModel, ActivityTaskDetailBinding>() {
    private val taskContentAdapter by lazy {
        TaskDetailAdapter()
    }

    override fun getContentViewId(): Int {
        return R.layout.activity_task_detail
    }

    override fun getVMClass(): Class<PunchViewModel> {
        return PunchViewModel::class.java
    }

    override fun setToolBarTitle(): String {
        return getString(R.string.task_detail_text)
    }

    override fun initView() {
        binding.taskContentRv.run {
            layoutManager = LinearLayoutManager(context)
            adapter = taskContentAdapter
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

    override fun loadData() {
        val taskName = intent.getStringExtra(Constants.TASK_NAME)
        viewModel.getPunchData(checkNotNull(taskName))
    }

    override fun observeData() {
        viewModel.punchData.observe(this) {
            initHeaderView(it.columns)
            taskContentAdapter.setData(it)
        }
    }

    private fun initHeaderView(columns: List<String>) {
        val headerContainer = binding.headerContainer
        for (i in 2 until columns.size - 1) {
            (headerContainer[i - 2] as TextView).text = columns[i]
        }
    }
}

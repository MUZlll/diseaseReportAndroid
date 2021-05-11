package com.muz1i.diseasereportandroid.view.fragment.punch

import android.view.ViewGroup
import android.widget.Button
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.muz1i.diseasereportandroid.R
import com.muz1i.diseasereportandroid.base.BaseApplication
import com.muz1i.diseasereportandroid.base.BaseFragment
import com.muz1i.diseasereportandroid.databinding.FragmentPunchBinding
import com.muz1i.diseasereportandroid.databinding.FragmentPunchManageBinding
import com.muz1i.diseasereportandroid.databinding.ItemPunchEditTextBinding
import com.muz1i.diseasereportandroid.utils.Constants
import com.muz1i.diseasereportandroid.utils.ToastUtils
import com.muz1i.diseasereportandroid.viewmodel.punch.PunchViewModel

/**
 * @author: Muz1i
 * @date: 2021/4/27
 */
class PunchManageFragment : BaseFragment<PunchViewModel, ViewDataBinding>() {
    private val itemBindingList by lazy {
        mutableListOf<ItemPunchEditTextBinding>()
    }

    private val contentList by lazy {
        mutableListOf<String>()
    }

    private val normalBinding by lazy {
        binding as FragmentPunchBinding
    }

    private val adminBinding by lazy {
        binding as FragmentPunchManageBinding
    }

    private val isAdmin by lazy {
        BaseApplication.permission == Constants.PERMISSION_ADMIN
    }

    override fun getVMClass(): Class<PunchViewModel> {
        return PunchViewModel::class.java
    }

    override fun getLayoutId(): Int {
        return if (isAdmin) {
            R.layout.fragment_punch_manage
        } else {
            R.layout.fragment_punch
        }
    }

    override fun initView() {
        if (isAdmin) {
            initAdminView()
        } else {
            initNormalView()
        }
    }

    private fun initNormalView() {
        viewModel.isPunched(BaseApplication.id)
    }

    override fun observeData() {
        val that = this
        viewModel.run {
            isPunched.observe(that) {
                if (it) {
                    showAlreadyPunchView()
                } else {
                    getPunchItems()
                }
            }
            punchItems.observe(that) {
                val subList = it.subList(2, it.size - 1)
                addPunchItemView(subList)
            }
            punchSuccess.observe(that) {
                if (it) {
                    ToastUtils.showToast("打卡成功")
                    showAlreadyPunchView()
                } else {
                    ToastUtils.showToast("打卡失败，请稍后重试")
                }
            }
            /**
             * ------------------------------------分割线------------------------------------
             */
        }
    }

    /**
     * ------------------------------------分割线------------------------------------
     */
    private fun addPunchItemView(items: List<String>) {
        normalBinding.editTextContainer.removeAllViews()
        for (item in items) {
            val itemBinding = DataBindingUtil.inflate<ItemPunchEditTextBinding>(
                layoutInflater,
                R.layout.item_punch_edit_text,
                normalBinding.editTextContainer as ViewGroup,
                true
            )
            itemBinding.itemName.text = "$item："
            itemBindingList.add(itemBinding)
        }
        addPunchCompleteBtn()
    }

    private fun addPunchCompleteBtn() {
        val punchBtnLayout =
            layoutInflater.inflate(
                R.layout.btn_complete_punch,
                normalBinding.editTextContainer as ViewGroup,
                true
            )
        val punchBtn = punchBtnLayout.findViewById<Button>(R.id.punch_btn)
        punchBtn.setOnClickListener {
            if (checkTextIsNull()) {
                ToastUtils.showToast("内容不能为空")
            } else {
                getContentList()
                viewModel.punch(contentList)
            }
        }
    }

    private fun getContentList() {
        contentList.clear()
        for (itemPunchEditTextBinding in itemBindingList) {
            contentList.add(itemPunchEditTextBinding.itemContent.text.toString())
        }
        contentList.add(BaseApplication.id)
    }

    private fun checkTextIsNull(): Boolean {
        for (itemPunchEditTextBinding in itemBindingList) {
            if (itemPunchEditTextBinding.itemContent.text.isEmpty()) {
                return true
            }
        }
        return false
    }

    private fun showAlreadyPunchView() {
        normalBinding.editTextContainer.run {
            removeAllViews()
            layoutInflater.inflate(R.layout.layout_already_punch, this, true)
        }
    }

    /**
     * ------------------------------------分割线------------------------------------
     */
    private fun initAdminView() {
        
    }
}

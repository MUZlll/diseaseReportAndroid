package com.muz1i.diseasereportandroid.view.activity

import android.widget.AutoCompleteTextView
import android.widget.TextView
import com.muz1i.diseasereportandroid.R
import com.muz1i.diseasereportandroid.adapter.MyArrayAdapter
import com.muz1i.diseasereportandroid.base.BaseActivity
import com.muz1i.diseasereportandroid.bean.UserInfoData
import com.muz1i.diseasereportandroid.databinding.ActivityUserDetailBinding
import com.muz1i.diseasereportandroid.utils.Constants
import com.muz1i.diseasereportandroid.utils.EditCheckUtils
import com.muz1i.diseasereportandroid.utils.FinishActivityAndToastUtils
import com.muz1i.diseasereportandroid.utils.ToastUtils
import com.muz1i.diseasereportandroid.viewmodel.manage.UserViewModel

/**
 * @author: Muz1i
 * @date: 2021/5/2
 */
class UserDetailActivity : BaseActivity<UserViewModel, ActivityUserDetailBinding>() {
    private lateinit var stuNum: String
    override fun getContentViewId(): Int {
        return R.layout.activity_user_detail
    }

    override fun getVMClass(): Class<UserViewModel> {
        return UserViewModel::class.java
    }

    override fun setToolBarTitle(): String {
        return getString(R.string.detail_text)
    }

    override fun initView() {
        val isAdd = intent.getBooleanExtra(Constants.IS_ADD_BUTTON_CLICK, false)
        binding.isAdd = isAdd
        if (!isAdd) {
            stuNum = intent.getStringExtra(Constants.STU_NUM)!!
            viewModel.getUserDetail(stuNum)
        } else {
            binding.viewModel = UserInfoData(null, "", "", "", "", "", "", "", "", "")
            findViewById<TextView>(R.id.toolbar_title).text = "添加用户"
        }
        (binding.sexLayout.editText as AutoCompleteTextView).setAdapter(
            MyArrayAdapter(
                this,
                R.layout.list_item,
                Constants.SEX_ITEMS
            )
        )
        (binding.instituteLayout.editText as AutoCompleteTextView).setAdapter(
            MyArrayAdapter(
                this,
                R.layout.list_item,
                Constants.INSTITUTE_ITEMS
            )
        )
    }

    override fun observeData() {
        viewModel.run {
            userDetail.observe(this@UserDetailActivity, {
                binding.viewModel = it
            })
            editSuccess.observe(this@UserDetailActivity, {
                FinishActivityAndToastUtils.finishActivity(
                    this@UserDetailActivity,
                    it,
                    "修改成功",
                    "修改失败，请稍后重试"
                )
            })
            addSuccess.observe(this@UserDetailActivity, {
                FinishActivityAndToastUtils.finishActivity(
                    this@UserDetailActivity,
                    it,
                    "创建成功",
                    "创建失败，请稍后重试"
                )
            })
        }
    }

    override fun initEvent() {
        binding.modifyBtn.setOnClickListener {
            if (EditCheckUtils.checkNotNull(rootView)) {
                val userInfo = binding.viewModel as UserInfoData
                viewModel.editUserInfo(userInfo)
            } else {
                ToastUtils.showToast("修改失败，内容不能为空")
            }
        }
        binding.addBtn.setOnClickListener {
            if (EditCheckUtils.checkNotNull(rootView)) {
                val userInfo = binding.viewModel as UserInfoData
                viewModel.addUser(userInfo)
            } else {
                ToastUtils.showToast("创建失败，内容不能为空")
            }
        }
    }
}

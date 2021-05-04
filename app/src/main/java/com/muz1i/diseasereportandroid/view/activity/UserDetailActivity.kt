package com.muz1i.diseasereportandroid.view.activity

import android.widget.ScrollView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.get
import com.google.android.material.textfield.TextInputLayout
import com.muz1i.diseasereportandroid.R
import com.muz1i.diseasereportandroid.base.BaseActivity
import com.muz1i.diseasereportandroid.bean.UserInfoData
import com.muz1i.diseasereportandroid.databinding.ActivityUserDetailBinding
import com.muz1i.diseasereportandroid.utils.Constants
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

    override fun initView() {
        val isAdd = intent.getBooleanExtra(Constants.IS_ADD_BUTTON_CLICK, false)
        binding.isAdd = isAdd
        if (!isAdd) {
            stuNum = intent.getStringExtra(Constants.STU_NUM)
            viewModel.getUserDetail(stuNum)
        } else {
            binding.viewModel = UserInfoData(null, "", "", "", "", "", "", "", "", "")
            binding.toolbarTitle.text = "添加用户"
        }
    }

    override fun observeData() {
        viewModel.run {
            userDetail.observe(this@UserDetailActivity, {
                binding.viewModel = it
            })
            editSuccess.observe(this@UserDetailActivity, {
                if (it) {
                    finish()
                    ToastUtils.showToast("修改成功")
                } else {
                    ToastUtils.showToast("修改失败，请稍后重试")
                }
            })
            addSuccess.observe(this@UserDetailActivity, {
                if (it) {
                    finish()
                    ToastUtils.showToast("创建成功")
                } else {
                    ToastUtils.showToast("创建失败，请稍后重试")
                }
            })
        }
    }

    override fun initEvent() {
        binding.modifyBtn.setOnClickListener {
            if (checkNotNull()) {
                val userInfo = binding.viewModel as UserInfoData
                viewModel.editUserInfo(userInfo)
            } else {
                ToastUtils.showToast("修改失败，内容不能为空")
            }
        }
        binding.addBtn.setOnClickListener {
            if (checkNotNull()) {
                val userInfo = binding.viewModel as UserInfoData
                viewModel.addUser(userInfo)
            } else {
                ToastUtils.showToast("创建失败，内容不能为空")
            }
        }
    }

    private fun checkNotNull(): Boolean {
        val scrollView = rootView as ScrollView
        val constraintLayout = scrollView[0] as ConstraintLayout
        for (i in 0 until constraintLayout.childCount) {
            if (constraintLayout[i] is TextInputLayout) {
                val textInputLayout = constraintLayout[i] as TextInputLayout
                if (textInputLayout.editText!!.text.isNullOrEmpty()) {
                    return false
                }
            }
        }
        return true
    }
}

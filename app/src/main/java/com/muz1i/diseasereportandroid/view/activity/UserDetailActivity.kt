package com.muz1i.diseasereportandroid.view.activity

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
        stuNum = intent.getStringExtra(Constants.STU_NUM)
    }

    override fun loadData() {
        viewModel.getUserDetail(stuNum)
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
        }
    }

    override fun initEvent() {
        binding.modifyBtn.setOnClickListener {
            if (checkNotNull()) {
                val userInfo = binding.viewModel as UserInfoData
                viewModel.editUserInfo(userInfo)
            } else {
                ToastUtils.showToast("修改失败，变量不能为空")
            }
        }
    }

    private fun checkNotNull(): Boolean {
        val constraintLayout = rootView as ConstraintLayout
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

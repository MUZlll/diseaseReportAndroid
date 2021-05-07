package com.muz1i.diseasereportandroid.view.activity

import android.widget.AutoCompleteTextView
import com.muz1i.diseasereportandroid.R
import com.muz1i.diseasereportandroid.adapter.MyArrayAdapter
import com.muz1i.diseasereportandroid.base.BaseActivity
import com.muz1i.diseasereportandroid.bean.DoctorInfoData
import com.muz1i.diseasereportandroid.databinding.ActivityDoctorDetailBinding
import com.muz1i.diseasereportandroid.utils.Constants
import com.muz1i.diseasereportandroid.utils.EditCheckUtils
import com.muz1i.diseasereportandroid.utils.ToastUtils
import com.muz1i.diseasereportandroid.viewmodel.manage.DoctorViewModel

/**
 * @author: Muz1i
 * @date: 2021/5/4
 */
class DoctorDetailActivity : BaseActivity<DoctorViewModel, ActivityDoctorDetailBinding>() {
    override fun getContentViewId(): Int {
        return R.layout.activity_doctor_detail
    }

    override fun getVMClass(): Class<DoctorViewModel> {
        return DoctorViewModel::class.java
    }

    override fun initView() {
        val isAdd = intent.getBooleanExtra(Constants.IS_ADD_BUTTON_CLICK, false)
        binding.isAdd = isAdd
        if (!isAdd) {
            val id = intent.getIntExtra(Constants.DOCTOR_ID, 0)
            viewModel.getDoctorDetail(id)
        } else {
            binding.viewModel = DoctorInfoData(null, "", "", "")
            binding.toolbarTitle.text = "添加用户"
        }
        val adapter = MyArrayAdapter(this, R.layout.list_item, Constants.SEX_ITEMS)
        (binding.sexLayout.editText as AutoCompleteTextView).setAdapter(adapter)
    }

    override fun observeData() {
        viewModel.run {
            doctorDetail.observe(this@DoctorDetailActivity, {
                binding.viewModel = it
            })
            editSuccess.observe(this@DoctorDetailActivity, {
                if (it) {
                    finish()
                    ToastUtils.showToast("修改成功")
                } else {
                    ToastUtils.showToast("修改失败，请稍后重试")
                }
            })
            addSuccess.observe(this@DoctorDetailActivity, {
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
            if (EditCheckUtils.checkNotNull(rootView)) {
                val doctorInfo = binding.viewModel as DoctorInfoData
                viewModel.editDoctorInfo(doctorInfo)
            }
        }
        binding.addBtn.setOnClickListener {
            if (EditCheckUtils.checkNotNull(rootView)) {
                val doctorInfo = binding.viewModel as DoctorInfoData
                viewModel.addDoctor(doctorInfo)
            } else {
                ToastUtils.showToast("创建失败，内容不能为空")
            }
        }
    }
}

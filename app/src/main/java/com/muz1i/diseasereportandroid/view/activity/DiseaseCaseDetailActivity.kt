package com.muz1i.diseasereportandroid.view.activity

import com.google.android.material.datepicker.MaterialDatePicker
import com.muz1i.diseasereportandroid.R
import com.muz1i.diseasereportandroid.base.BaseActivity
import com.muz1i.diseasereportandroid.bean.DiseaseCaseData
import com.muz1i.diseasereportandroid.bean.UserInfoData
import com.muz1i.diseasereportandroid.databinding.ActivityDiseaseCaseDetailBinding
import com.muz1i.diseasereportandroid.utils.Constants
import com.muz1i.diseasereportandroid.utils.ToastUtils
import com.muz1i.diseasereportandroid.viewmodel.diseasecase.DiseaseCaseViewModel
import java.text.SimpleDateFormat
import java.util.*

/**
 * @author: Muz1i
 * @date: 2021/5/8
 */
class DiseaseCaseDetailActivity :
    BaseActivity<DiseaseCaseViewModel, ActivityDiseaseCaseDetailBinding>() {
    private val datePicker by lazy {
        MaterialDatePicker.Builder.datePicker()
            .setTitleText("选择日期")
            .setSelection(MaterialDatePicker.todayInUtcMilliseconds())
            .build()
    }

    override fun getContentViewId(): Int {
        return R.layout.activity_disease_case_detail
    }

    override fun getVMClass(): Class<DiseaseCaseViewModel> {
        return DiseaseCaseViewModel::class.java
    }

    override fun setToolBarTitle(): String {
        return getString(R.string.disease_case_detail_toolbar_text)
    }

    override fun loadData() {
        val diseaseCaseId = intent.getIntExtra(Constants.DISEASE_CASE_ID, 0)
        viewModel.getDiseaseCaseById(diseaseCaseId)
    }

    override fun observeData() {
        viewModel.run {
            diseaseCase.observe(this@DiseaseCaseDetailActivity, {
                binding.viewModel = it
            })
            editSuccess.observe(this@DiseaseCaseDetailActivity, {
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
        binding.run {
            createTimeLayout.editText?.setOnFocusChangeListener { v, hasFocus ->
                if (hasFocus) {
                    datePicker.show(supportFragmentManager, "tag")
                }
            }
            createTimeLayout.editText?.setOnClickListener {
                datePicker.show(supportFragmentManager, "tag")
            }
            modifyBtn.setOnClickListener {
                viewModel?.run {
                    val diseaseCaseData = DiseaseCaseData(
                        id,
                        UserInfoData(id_user),
                        disease_level,
                        disease_name,
                        create_time,
                        remark
                    )
                    this@DiseaseCaseDetailActivity.viewModel.editDiseaseCase(diseaseCaseData)
                }
            }
        }
        datePicker.addOnPositiveButtonClickListener {
            val date = Date(it)
            val format = SimpleDateFormat("yyyy-MM-dd", Locale.CHINA).format(date)
            binding.createTimeLayout.editText?.setText(format)
        }
    }
}

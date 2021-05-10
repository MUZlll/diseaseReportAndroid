package com.muz1i.diseasereportandroid.view.activity

import android.widget.AutoCompleteTextView
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.datepicker.MaterialDatePicker
import com.muz1i.diseasereportandroid.R
import com.muz1i.diseasereportandroid.adapter.MyArrayAdapter
import com.muz1i.diseasereportandroid.base.BaseActivity
import com.muz1i.diseasereportandroid.bean.DiseaseCaseData
import com.muz1i.diseasereportandroid.bean.UserInfoData
import com.muz1i.diseasereportandroid.databinding.ActivityDiseaseCaseAddBinding
import com.muz1i.diseasereportandroid.utils.Constants
import com.muz1i.diseasereportandroid.utils.FinishActivityAndToastUtils
import com.muz1i.diseasereportandroid.utils.ToastUtils
import com.muz1i.diseasereportandroid.viewmodel.diseasecase.DiseaseCaseViewModel
import com.muz1i.diseasereportandroid.viewmodel.manage.UserViewModel
import java.text.SimpleDateFormat
import java.util.*

/**
 * @author: Muz1i
 * @date: 2021/5/8
 */
class DiseaseCaseAddActivity : BaseActivity<DiseaseCaseViewModel, ActivityDiseaseCaseAddBinding>() {

    private val userViewModel by lazy {
        ViewModelProvider(this).get(UserViewModel::class.java)
    }

    private val datePicker by lazy {
        MaterialDatePicker.Builder.datePicker()
            .setTitleText("选择日期")
            .setSelection(MaterialDatePicker.todayInUtcMilliseconds())
            .build()
    }

    override fun getContentViewId(): Int {
        return R.layout.activity_disease_case_add
    }

    override fun getVMClass(): Class<DiseaseCaseViewModel> {
        return DiseaseCaseViewModel::class.java
    }

    override fun setToolBarTitle(): String {
        return getString(R.string.add_disease_case_text)
    }

    override fun initView() {
        binding.run {
            userViewModel = UserInfoData(null)
            (addDiseaseLevel.editText as AutoCompleteTextView).setAdapter(
                MyArrayAdapter(
                    this@DiseaseCaseAddActivity,
                    R.layout.list_item,
                    Constants.LEVEL_ITEMS
                )
            )
            diseaseViewModel = DiseaseCaseData(null, UserInfoData(null), "", "", "", "")
        }
    }

    override fun initEvent() {
        val that = this
        binding.run {
            searchBtn.setOnClickListener {
                if (searchContent.text.isNullOrEmpty()) {
                    ToastUtils.showToast("请输入用户学号")
                } else {
                    that.userViewModel.getUserDetail(searchContent.text.toString())
                }
            }
            addDiseaseCreateTime.editText?.setOnFocusChangeListener { _, hasFocus ->
                if (hasFocus) {
                    datePicker.show(supportFragmentManager, "tag")
                }
            }
            addDiseaseCreateTime.editText?.setOnClickListener {
                datePicker.show(supportFragmentManager, "tag")
            }
            addDiseaseCaseBtn.setOnClickListener {
                if (checkIsNull()) {
                    ToastUtils.showToast("所有内容均不能为空")
                } else {
                    diseaseViewModel?.user?.id = userViewModel?.id
                    that.viewModel.addDiseaseCase(diseaseViewModel!!)
                }
            }
            datePicker.addOnPositiveButtonClickListener {
                val date = Date(it)
                val format = SimpleDateFormat("yyyy-MM-dd", Locale.CHINA).format(date)
                addDiseaseCreateTime.editText?.setText(format)
            }
        }
    }

    private fun checkIsNull(): Boolean {
        return binding.run {
            userViewModel == null || addDiseaseName.editText?.text.isNullOrEmpty()
                    || (addDiseaseLevel.editText as AutoCompleteTextView).text.isNullOrEmpty()
                    || addDiseaseCreateTime.editText?.text.isNullOrEmpty()
                    || addDiseaseRemark.editText?.text.isNullOrEmpty()
        }
    }

    override fun observeData() {
        userViewModel.userDetail.observe(this) {
            if (it == null) {
                ToastUtils.showToast("请检查学号是否有误")
            } else {
                binding.userViewModel = it
            }
        }
        viewModel.addSuccess.observe(this) {
            FinishActivityAndToastUtils.finishActivity(
                this,
                it,
                "添加成功",
                "添加失败，请稍后重试"
            )
        }
    }
}

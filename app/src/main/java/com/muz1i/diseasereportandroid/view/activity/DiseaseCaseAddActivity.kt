package com.muz1i.diseasereportandroid.view.activity

import com.muz1i.diseasereportandroid.R
import com.muz1i.diseasereportandroid.base.BaseActivity
import com.muz1i.diseasereportandroid.databinding.ActivityDiseaseCaseAddBinding
import com.muz1i.diseasereportandroid.viewmodel.diseasecase.DiseaseCaseViewModel

/**
 * @author: Muz1i
 * @date: 2021/5/8
 */
class DiseaseCaseAddActivity : BaseActivity<DiseaseCaseViewModel, ActivityDiseaseCaseAddBinding>() {
    override fun getContentViewId(): Int {
        return R.layout.activity_disease_case_add
    }

    override fun getVMClass(): Class<DiseaseCaseViewModel> {
        return DiseaseCaseViewModel::class.java
    }

    override fun setToolBarTitle(): String {
        return getString(R.string.add_disease_case_text)
    }
    
    
}

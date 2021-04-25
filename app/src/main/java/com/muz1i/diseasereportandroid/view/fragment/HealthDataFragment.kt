package com.muz1i.diseasereportandroid.view.fragment

import com.muz1i.diseasereportandroid.R
import com.muz1i.diseasereportandroid.base.BaseFragment
import com.muz1i.diseasereportandroid.databinding.FragmentHealthDataBinding
import com.muz1i.diseasereportandroid.viewmodel.HealthDataViewModel

/**
 * @author: Muz1i
 * @date: 2021/4/25
 */
class HealthDataFragment : BaseFragment<HealthDataViewModel, FragmentHealthDataBinding>() {
    override fun getVMClass(): Class<HealthDataViewModel> {
        return HealthDataViewModel::class.java
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_health_data
    }
}

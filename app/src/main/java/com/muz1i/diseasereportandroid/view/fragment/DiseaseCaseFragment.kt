package com.muz1i.diseasereportandroid.view.fragment

import com.muz1i.diseasereportandroid.R
import com.muz1i.diseasereportandroid.base.BaseFragment
import com.muz1i.diseasereportandroid.databinding.FragmentDiseaseCaseBinding
import com.muz1i.diseasereportandroid.viewmodel.DiseaseCaseViewModel

/**
 * @author: Muz1i
 * @date: 2021/4/25
 */
class DiseaseCaseFragment : BaseFragment<DiseaseCaseViewModel, FragmentDiseaseCaseBinding>() {
    override fun getVMClass(): Class<DiseaseCaseViewModel> {
        return DiseaseCaseViewModel::class.java
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_disease_case
    }
}

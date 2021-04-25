package com.muz1i.diseasereportandroid.view.fragment

import com.muz1i.diseasereportandroid.R
import com.muz1i.diseasereportandroid.base.BaseFragment
import com.muz1i.diseasereportandroid.databinding.FragmentPunchBinding
import com.muz1i.diseasereportandroid.viewmodel.PunchViewModel

/**
 * @author: Muz1i
 * @date: 2021/4/25
 */
class PunchFragment : BaseFragment<PunchViewModel, FragmentPunchBinding>() {
    override fun getVMClass(): Class<PunchViewModel> {
        return PunchViewModel::class.java
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_punch
    }
}

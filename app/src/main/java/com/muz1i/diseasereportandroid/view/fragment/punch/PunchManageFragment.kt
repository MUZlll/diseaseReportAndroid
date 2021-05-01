package com.muz1i.diseasereportandroid.view.fragment.punch

import com.muz1i.diseasereportandroid.R
import com.muz1i.diseasereportandroid.base.BaseFragment
import com.muz1i.diseasereportandroid.databinding.FragmentPunchManageBinding
import com.muz1i.diseasereportandroid.viewmodel.punch.PunchManageViewModel

/**
 * @author: Muz1i
 * @date: 2021/4/27
 */
class PunchManageFragment : BaseFragment<PunchManageViewModel, FragmentPunchManageBinding>() {
    override fun getVMClass(): Class<PunchManageViewModel> {
        return PunchManageViewModel::class.java
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_punch_manage
    }
}

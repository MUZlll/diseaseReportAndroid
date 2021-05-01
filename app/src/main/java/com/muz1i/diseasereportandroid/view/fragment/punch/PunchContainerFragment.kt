package com.muz1i.diseasereportandroid.view.fragment.punch

import com.muz1i.diseasereportandroid.R
import com.muz1i.diseasereportandroid.base.BaseFragment
import com.muz1i.diseasereportandroid.databinding.FragmentPunchContainerBinding
import com.muz1i.diseasereportandroid.viewmodel.punch.PunchViewModel

/**
 * @author: Muz1i
 * @date: 2021/4/25
 */
class PunchContainerFragment : BaseFragment<PunchViewModel, FragmentPunchContainerBinding>() {
    override fun getVMClass(): Class<PunchViewModel> {
        return PunchViewModel::class.java
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_punch_container
    }
}

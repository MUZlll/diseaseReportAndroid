package com.muz1i.diseasereportandroid.view.fragment

import com.muz1i.diseasereportandroid.R
import com.muz1i.diseasereportandroid.base.BaseFragment
import com.muz1i.diseasereportandroid.databinding.FragmentPersonalInfoBinding
import com.muz1i.diseasereportandroid.viewmodel.PersonalInfoViewModel

/**
 * @author: Muz1i
 * @date: 2021/4/25
 */
class PersonalInfoFragment : BaseFragment<PersonalInfoViewModel, FragmentPersonalInfoBinding>() {
    override fun getVMClass(): Class<PersonalInfoViewModel> {
        return PersonalInfoViewModel::class.java
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_personal_info
    }

    override fun loadData() {
        viewModel.getUserInfo("201730685500")
    }

    override fun observeData() {
        viewModel.userInfo.observe(this) {
            binding.userInfo = it
        }
    }

    override fun reload() {
        loadData()
    }
}

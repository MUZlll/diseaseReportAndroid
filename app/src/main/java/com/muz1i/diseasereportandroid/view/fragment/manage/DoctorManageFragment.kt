package com.muz1i.diseasereportandroid.view.fragment.manage

import com.muz1i.diseasereportandroid.R
import com.muz1i.diseasereportandroid.base.BaseFragment
import com.muz1i.diseasereportandroid.databinding.FragmentDoctorMangeBinding
import com.muz1i.diseasereportandroid.utils.ToastUtils
import com.muz1i.diseasereportandroid.viewmodel.manage.DoctorViewModel

/**
 * @author: Muz1i
 * @date: 2021/4/29
 */
class DoctorManageFragment : BaseFragment<DoctorViewModel, FragmentDoctorMangeBinding>() {
    override fun getVMClass(): Class<DoctorViewModel> {
        return DoctorViewModel::class.java
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_doctor_mange
    }

    override fun initEvent() {

    }

    override fun onResume() {
        val userManageContainerFragment = parentFragment as UserManageContainerFragment
        userManageContainerFragment.setOnAddBtnClickListener {
            ToastUtils.showToast("添加医生用户")
        }
        super.onResume()
    }
}

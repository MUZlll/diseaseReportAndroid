package com.muz1i.diseasereportandroid.view.fragment

import com.muz1i.diseasereportandroid.R
import com.muz1i.diseasereportandroid.base.BaseFragment
import com.muz1i.diseasereportandroid.databinding.FragmentUserMangeBinding
import com.muz1i.diseasereportandroid.viewmodel.UserManageViewModel

/**
 * @author: Muz1i
 * @date: 2021/4/25
 */
class UserManageFragment : BaseFragment<UserManageViewModel, FragmentUserMangeBinding>() {
    override fun getVMClass(): Class<UserManageViewModel> {
        return UserManageViewModel::class.java
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_user_mange
    }
}

package com.muz1i.diseasereportandroid.view.fragment.manage

import androidx.fragment.app.Fragment
import com.muz1i.diseasereportandroid.base.BaseViewContainerFragment
import java.util.*

/**
 * @author: Muz1i
 * @date: 2021/4/25
 */
class UserManageContainerFragment : BaseViewContainerFragment() {
    override fun getTabFragmentList(): Collection<Fragment> {
        return arrayListOf(UserManageFragment(), DoctorManageFragment())
    }

    override fun getTabList(): ArrayList<String> {
        return arrayListOf("用户管理", "医生管理")
    }

    fun setOnAddBtnClickListener(onClick: () -> Unit) {
        rootBinding.addButton.setOnClickListener {
            onClick()
        }
    }
}

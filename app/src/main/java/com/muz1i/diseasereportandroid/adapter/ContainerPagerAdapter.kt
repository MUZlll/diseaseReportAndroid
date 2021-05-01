package com.muz1i.diseasereportandroid.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

/**
 * @author: Muz1i
 * @date: 2021/4/29
 */
class ContainerPagerAdapter(fm: FragmentManager) :
    FragmentPagerAdapter(fm, FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    val tabList by lazy {
        ArrayList<String>()
    }

    val tabFragmentList by lazy {
        ArrayList<Fragment>()
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return tabList[position]
    }

    override fun getCount(): Int {
        return tabList.size
    }

    override fun getItem(position: Int): Fragment {
        return tabFragmentList[position]
    }
}

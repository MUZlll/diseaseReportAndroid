package com.muz1i.diseasereportandroid.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.muz1i.diseasereportandroid.R
import com.muz1i.diseasereportandroid.adapter.ContainerPagerAdapter
import com.muz1i.diseasereportandroid.databinding.FragmentBaseContainerBinding
import java.util.*

/**
 * @author: Muz1i
 * @date: 2021/4/29
 */
abstract class BaseViewContainerFragment : Fragment() {
    lateinit var rootBinding: FragmentBaseContainerBinding

    private val containerAdapter by lazy {
        ContainerPagerAdapter(childFragmentManager)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        rootBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_base_container,
            container,
            false
        )
        initAdapter()
        initView()
        initData()
        initEvent()
        return rootBinding.root
    }

    private fun initAdapter() {
        containerAdapter.tabList.addAll(getTabList())
        containerAdapter.tabFragmentList.addAll(getTabFragmentList())
        rootBinding.run {
            containerPager.adapter = containerAdapter
            containerTab.setupWithViewPager(containerPager)
        }
    }

    abstract fun getTabFragmentList(): Collection<Fragment>

    abstract fun getTabList(): ArrayList<String>

    open fun initEvent() {

    }

    open fun initView() {

    }

    open fun initData() {

    }
}

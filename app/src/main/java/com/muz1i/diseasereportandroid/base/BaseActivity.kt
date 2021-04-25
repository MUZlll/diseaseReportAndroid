package com.muz1i.diseasereportandroid.base

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModelProvider

/**
 * @author: Muz1i
 * @date: 2021/4/22
 */
abstract class BaseActivity<VM : BaseViewModel, Binding : ViewDataBinding> :
    AppCompatActivity() {

    lateinit var viewModel: VM
    lateinit var binding: Binding
    lateinit var rootView: View
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //初始化binding
        binding = DataBindingUtil.setContentView(this, getContentViewId())
        binding.lifecycleOwner = this
        rootView = binding.root
        initViewModel()
        observeData()
        initView()
        initEvent()
        loadData()
    }

    open fun initView() {

    }

    open fun loadData() {

    }

    open fun initEvent() {

    }

    open fun observeData() {

    }

    private fun initViewModel() {
        viewModel = ViewModelProvider(this).get(getVMClass())
    }

    abstract fun getContentViewId(): Int

    abstract fun getVMClass(): Class<VM>


}

package com.muz1i.diseasereportandroid.base

import android.os.Bundle
import android.view.View
import android.widget.FrameLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModelProvider
import com.muz1i.diseasereportandroid.R

/**
 * @author: Muz1i
 * @date: 2021/4/22
 */
abstract class BaseActivity<VM : BaseViewModel, VB : ViewDataBinding> :
    AppCompatActivity() {

    lateinit var viewModel: VM
    lateinit var binding: VB
    lateinit var rootView: View
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_base)
        //初始化binding
        binding =
            DataBindingUtil.inflate(layoutInflater, getContentViewId(), null, false)
        binding.lifecycleOwner = this
        rootView = binding.root
        findViewById<FrameLayout>(R.id.activity_container).addView(rootView)
        findViewById<TextView>(R.id.toolbar_title).text = setToolBarTitle()
        initViewModel()
        observeData()
        initView()
        initEvent()
        loadData()
    }

    open fun setToolBarTitle(): String {
        return getString(R.string.main_activity_title)
    }

    open fun setToolBarVisibility(): Int {
        return View.VISIBLE
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

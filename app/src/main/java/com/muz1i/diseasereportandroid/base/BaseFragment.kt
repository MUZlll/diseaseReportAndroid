package com.muz1i.diseasereportandroid.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.muz1i.diseasereportandroid.R
import com.muz1i.diseasereportandroid.databinding.FragmentBaseBinding
import com.muz1i.diseasereportandroid.utils.LoadState

/**
 * @author: Muz1i
 * @date: 2021/4/24
 */
abstract class BaseFragment<VM : BaseViewModel, VB : ViewDataBinding> : Fragment() {

    lateinit var binding: VB
    lateinit var viewModel: VM
    lateinit var rootView: View
    private lateinit var baseContainer: FrameLayout
    private lateinit var loadingView: View
    private lateinit var emptyView: View
    private lateinit var errorView: View
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootBinding =
            DataBindingUtil.inflate<FragmentBaseBinding>(
                inflater,
                R.layout.fragment_base,
                container,
                false
            )
        baseContainer = rootBinding.container
        //创建ViewModel
        initViewModel()
        //观察ViewModel数据的变化->更新UI
        observeData()
        loadStateView(inflater, container)
        //观察BaseViewModel的State的状态->更新页面状态
        observeState()
        initView()
        //设置相关事件
        initEvent()
        loadData()
        return rootBinding.root
    }

    open fun initView() {

    }

    private fun loadStateView(inflater: LayoutInflater, container: ViewGroup?) {
        //加载有数据的布局即success布局
        binding = DataBindingUtil.inflate(inflater, getLayoutId(), container, false)
        binding.lifecycleOwner = this
        rootView = binding.root
        baseContainer.addView(rootView)
        loadingView = loadLoadingView(inflater, container)
        baseContainer.addView(loadingView)
        emptyView = loadEmptyView(inflater, container)
        baseContainer.addView(emptyView)
        errorView = loadErrorView(inflater, container)
        baseContainer.addView(errorView)
        viewModel.loadState.value = LoadState.SUCCESS
    }

    private fun observeState() {
        viewModel.loadState.observe(viewLifecycleOwner, {
            rootView.visibility = if (it == LoadState.SUCCESS) View.VISIBLE else View.GONE
            loadingView.visibility = if (it == LoadState.LOADING) View.VISIBLE else View.GONE
            emptyView.visibility = if (it == LoadState.EMPTY) View.VISIBLE else View.GONE
            errorView.visibility = if (it == LoadState.ERROR) View.VISIBLE else View.GONE
        })
    }

    open fun loadData() {

    }

    private fun initViewModel() {
        viewModel = ViewModelProvider(this).get(getVMClass())
    }

    open fun loadLoadingView(inflater: LayoutInflater, container: ViewGroup?): View {
        return inflater.inflate(R.layout.fragment_loading, container, false)
    }

    open fun loadEmptyView(inflater: LayoutInflater, container: ViewGroup?): View {
        return inflater.inflate(R.layout.fragment_empty, container, false)
    }

    open fun loadErrorView(inflater: LayoutInflater, container: ViewGroup?): View {
        return inflater.inflate(R.layout.fragment_error, container, false)
    }

    open fun initEvent() {

    }

    open fun observeData() {

    }

    abstract fun getVMClass(): Class<VM>

    abstract fun getLayoutId(): Int
}

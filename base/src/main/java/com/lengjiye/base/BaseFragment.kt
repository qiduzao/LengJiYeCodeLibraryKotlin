package com.lengjiye.base

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.lengjiye.base.viewmode.BaseViewMode

/**
 * 基类
 */
abstract class BaseFragment<T : ViewDataBinding, VM : BaseViewMode> : Fragment() {
    lateinit var mBinding: T
    lateinit var mViewModel: VM

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mBinding = DataBindingUtil.inflate(inflater, getLayoutId(), container, false)
        mViewModel = ViewModelProvider(getBaseActivity()).get(getViewModel()::class.java)
        mBinding.lifecycleOwner = this
        bindViewModel()
        mViewModel.onCreate()
        initView(savedInstanceState)
        return mBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initData()
    }

    abstract fun getLayoutId(): Int

    abstract fun getViewModel(): VM

    /**
     * 绑定 ViewModel
     */
    abstract fun bindViewModel()

    /**
     * 获取 mBinding
     */
    abstract fun getBinding(): T

    /**
     * 初始化 view
     */
    open fun initView(savedInstanceState: Bundle?) = Unit

    open fun initData() = Unit

    fun getBaseActivity(): BaseActivity<*, *> {
        return activity as BaseActivity<*, *>
    }

    override fun onDestroyView() {
        super.onDestroyView()
        mViewModel.onDestroy()
    }
}
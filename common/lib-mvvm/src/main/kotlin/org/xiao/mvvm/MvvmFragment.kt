package org.xiao.mvvm

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

abstract class MvvmFragment<V : ViewDataBinding, M : ViewModel> : Fragment() {
    lateinit var mBinding: V
    lateinit var mModel: M

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mBinding = DataBindingUtil.inflate<V>(layoutInflater, getRootLayoutId(), container, false)
        mModel = ViewModelProvider(this).get(getViewModelClass())
        return mBinding.root
    }

    abstract fun getRootLayoutId(): Int

    abstract fun getViewModelClass(): Class<M>
}
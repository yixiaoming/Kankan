package org.xiao.mvvm

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

open abstract class MvvmActivity<V : ViewDataBinding, M : ViewModel> : AppCompatActivity() {

    lateinit var mBinding: V
    lateinit var mModel: M

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView<V>(this, getRootLayoutId())
        mModel = ViewModelProvider(this).get(getViewModelClass())
    }

    abstract fun getRootLayoutId(): Int

    abstract fun getViewModelClass(): Class<M>
}
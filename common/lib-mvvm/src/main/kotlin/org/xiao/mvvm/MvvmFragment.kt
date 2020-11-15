package org.xiao.mvvm

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

abstract class MvvmFragment<M : ViewModel> : Fragment() {
    lateinit var mModel: M

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var rootView = inflater.inflate(getRootLayoutId(), container, false)
        mModel = ViewModelProvider(this).get(getViewModelClass())
        return rootView
    }

    abstract fun getRootLayoutId(): Int

    abstract fun getViewModelClass(): Class<M>
}
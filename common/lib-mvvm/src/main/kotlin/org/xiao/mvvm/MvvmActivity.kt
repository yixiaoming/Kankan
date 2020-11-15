package org.xiao.mvvm

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

abstract class MvvmActivity<M : ViewModel> : AppCompatActivity() {

    lateinit var mModel: M

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getRootLayoutId())
        mModel = ViewModelProvider(this).get(getViewModelClass())
    }

    abstract fun getRootLayoutId(): Int

    abstract fun getViewModelClass(): Class<M>
}
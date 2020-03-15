package org.xiao.kankan

import android.os.Bundle
import org.xiao.kankan.databinding.ActivityMainBinding
import org.xiao.mvvm.MvvmActivity

class MainActivity : MvvmActivity<ActivityMainBinding, MainViewModel>() {

    private val mMainFragment by lazy {
        MainFragment.newInstance()
    }

    override fun getRootLayoutId(): Int {
        return R.layout.activity_main;
    }

    override fun getViewModelClass(): Class<MainViewModel> {
        return MainViewModel::class.java
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportFragmentManager.beginTransaction().replace(R.id.fragment_container, mMainFragment).commit()
    }
}
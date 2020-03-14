package org.xiao.kankan

import android.os.Bundle
import org.xiao.mvvm.MvvmActivity
import org.xiao.kankan.databinding.ActivityMainBinding

class MainActivity : MvvmActivity<ActivityMainBinding, MainViewModel>() {

    private val mMainFragment by lazy {
        MainFragment.newInstance("")
    }

    override fun getRootLayoutId(): Int {
        return R.layout.activity_main;
    }

    override fun getViewModelClass(): Class<MainViewModel> {
        return MainViewModel::class.java
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportFragmentManager.beginTransaction().replace(R.id.main_container, mMainFragment).commit()
    }
}
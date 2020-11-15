package org.xiao.kankan

import android.os.Bundle
import android.util.Log
import org.xiao.mvvm.MvvmActivity

class MainActivity : MvvmActivity<MainViewModel>() {

    companion object {
        const val TAG = "MainActivity"
    }

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
        Log.d(TAG, "onCreate: $MainFragment");
        supportFragmentManager.beginTransaction().replace(R.id.fragment_container, mMainFragment).commit()
    }
}
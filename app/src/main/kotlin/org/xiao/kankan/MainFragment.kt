package org.xiao.kankan

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import org.xiao.mvvm.MvvmFragment
import org.xiao.kankan.databinding.MainFragmentBinding

class MainFragment : MvvmFragment<MainFragmentBinding, MainViewModel>() {

    override fun getRootLayoutId(): Int {
        return R.layout.main_fragment
    }

    override fun getViewModelClass(): Class<MainViewModel> {
        return MainViewModel::class.java
    }

    companion object{
        fun newInstance(key1: String): MainFragment {
            val mainFragment = MainFragment()
            val arguments = mainFragment.arguments
            return mainFragment
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mModel.user.observe(viewLifecycleOwner, Observer {
            mBinding.text.text = it
        })
        mBinding.text.setOnClickListener {
            mModel.user.value = "123123"
        }
    }
}
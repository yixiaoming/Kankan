package org.xiao.kankan.home.findmore

import org.xiao.kankan.home.R
import org.xiao.kankan.home.databinding.FindmoreFragmentBinding
import org.xiao.mvvm.MvvmFragment

class FindMoreFragment : MvvmFragment<FindmoreFragmentBinding, FindmoreViewModel>() {
    companion object {
        fun newInstance(): FindMoreFragment {
            return FindMoreFragment()
        }
    }

    override fun getRootLayoutId(): Int {
        return R.layout.findmore_fragment
    }

    override fun getViewModelClass(): Class<FindmoreViewModel> {
        return FindmoreViewModel::class.java
    }
}
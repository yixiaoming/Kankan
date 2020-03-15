package org.xiao.kankan.home.daily

import org.xiao.kankan.home.R
import org.xiao.kankan.home.databinding.DailyFragmentBinding
import org.xiao.kankan.home.findmore.FindMoreFragment
import org.xiao.mvvm.MvvmFragment

class DailyFragment : MvvmFragment<DailyFragmentBinding, DailyViewModel>() {
    companion object {
        fun newInstance(): DailyFragment {
            return DailyFragment()
        }
    }

    override fun getRootLayoutId(): Int {
        return R.layout.daily_fragment
    }

    override fun getViewModelClass(): Class<DailyViewModel> {
        return DailyViewModel::class.java
    }
}
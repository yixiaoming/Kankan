package org.xiao.kankan.home.recommend

import org.xiao.kankan.home.R
import org.xiao.kankan.home.databinding.RecommendFragmentBinding
import org.xiao.mvvm.MvvmFragment

class RecommendFragment : MvvmFragment<RecommendFragmentBinding, RecommendViewModel>() {
    companion object {
        fun newInstance(): RecommendFragment {
            return RecommendFragment()
        }
    }

    override fun getRootLayoutId(): Int {
        return R.layout.recommend_fragment
    }

    override fun getViewModelClass(): Class<RecommendViewModel> {
        return RecommendViewModel::class.java
    }
}
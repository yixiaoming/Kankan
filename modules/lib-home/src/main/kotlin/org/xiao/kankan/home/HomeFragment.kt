package org.xiao.kankan.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import org.xiao.kankan.home.daily.FeedFragment
import org.xiao.kankan.home.databinding.HomeFragmentBinding
import org.xiao.kankan.home.findmore.FindMoreFragment
import org.xiao.kankan.home.recommend.RecommendFragment
import org.xiao.mvvm.MvvmFragment

class HomeFragment : MvvmFragment<HomeFragmentBinding, HomeViewModel>() {

    companion object {
        fun newInstance(): HomeFragment {
            return HomeFragment()
        }
    }

    lateinit var pagerAdapter: HomePagerAdapter

    override fun getRootLayoutId(): Int {
        return R.layout.home_fragment
    }

    override fun getViewModelClass(): Class<HomeViewModel> {
        return HomeViewModel::class.java
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        pagerAdapter = HomePagerAdapter(childFragmentManager)
        var fragments = mutableListOf<Fragment>(
                FindMoreFragment.newInstance(),
                RecommendFragment.newInstance(),
                FeedFragment.newInstance()
        )
        pagerAdapter.setFragments(fragments)
        mBinding.viewpager.adapter = pagerAdapter
        mBinding.viewpager.currentItem = 1
    }

}
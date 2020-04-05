package org.xiao.kankan.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import org.xiao.kankan.home.databinding.HomeFragmentBinding
import org.xiao.kankan.home.feed.FeedFragment
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
        val fragments = mutableListOf<Fragment>(
                FindMoreFragment.newInstance(),
                RecommendFragment.newInstance(),
                FeedFragment.newInstance()
        )
        mBinding.viewpager.offscreenPageLimit = 0
        pagerAdapter.setFragments(fragments)
        mBinding.viewpager.adapter = pagerAdapter
        mBinding.homeToolbar.tabLayout.setTitles(arrayOf("发现", "推荐", "日报"))
        mBinding.homeToolbar.tabLayout.setViewPager(mBinding.viewpager)
        mBinding.viewpager.currentItem = 1
    }
}
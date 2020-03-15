package org.xiao.kankan.home

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.viewpager2.adapter.FragmentStateAdapter

class HomePagerAdapter(fm: FragmentManager) : FragmentStatePagerAdapter(fm) {
    private lateinit var mFragmentList: MutableList<Fragment>

    override fun getItem(position: Int): Fragment {
        return mFragmentList[position]
    }

    override fun getCount(): Int {
        return mFragmentList.size
    }

    fun setFragments(fragments: MutableList<Fragment>) {
        mFragmentList = fragments
    }
}
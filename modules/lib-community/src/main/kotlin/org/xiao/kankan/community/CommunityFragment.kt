package org.xiao.kankan.community

import org.xiao.mvvm.MvvmFragment

class CommunityFragment : MvvmFragment<CommunityViewModel>() {
    companion object {
        fun newInstance(): CommunityFragment {
            return CommunityFragment()
        }
    }

    override fun getRootLayoutId(): Int {
        return R.layout.community_fragment
    }

    override fun getViewModelClass(): Class<CommunityViewModel> {
        return CommunityViewModel::class.java
    }
}
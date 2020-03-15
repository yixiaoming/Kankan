package org.xiao.kankan.community

import org.xiao.kankan.community.databinding.CommunityFragmentBinding
import org.xiao.mvvm.MvvmFragment

class CommunityFragment : MvvmFragment<CommunityFragmentBinding, CommunityViewModel>() {
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
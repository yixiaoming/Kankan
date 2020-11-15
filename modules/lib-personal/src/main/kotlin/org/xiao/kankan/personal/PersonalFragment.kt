package org.xiao.kankan.personal

import org.xiao.mvvm.MvvmFragment

class PersonalFragment : MvvmFragment<PersonalViewModel>() {
    companion object {
        fun newInstance(): PersonalFragment {
            return PersonalFragment()
        }
    }

    override fun getRootLayoutId(): Int {
        return R.layout.personal_fragment
    }

    override fun getViewModelClass(): Class<PersonalViewModel> {
        return PersonalViewModel::class.java
    }
}
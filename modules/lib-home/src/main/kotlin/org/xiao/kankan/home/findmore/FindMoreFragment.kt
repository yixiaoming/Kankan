package org.xiao.kankan.home.findmore

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import org.xiao.kankan.home.R
import org.xiao.kankan.home.adapter.CardAdapter
import org.xiao.kankan.home.databinding.FindmoreFragmentBinding
import org.xiao.mvvm.MvvmFragment

class FindMoreFragment : MvvmFragment<FindmoreFragmentBinding, FindmoreViewModel>() {
    companion object {
        fun newInstance(): FindMoreFragment {
            return FindMoreFragment()
        }
    }

    private val cardAdapter by lazy {
        CardAdapter()
    }

    override fun getRootLayoutId(): Int {
        return R.layout.findmore_fragment
    }

    override fun getViewModelClass(): Class<FindmoreViewModel> {
        return FindmoreViewModel::class.java
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        initObservers()
        mModel.loadData()
    }

    private fun initViews() {
        mBinding.recyclerview.layoutManager = LinearLayoutManager(context)
        mBinding.recyclerview.adapter = cardAdapter
    }

    private fun initObservers() {
        mModel.mCardList.observe(viewLifecycleOwner, Observer {
            cardAdapter.initData(it.itemList)
        })
    }
}
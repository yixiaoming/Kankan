package org.xiao.kankan.home.daily

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import org.xiao.kankan.home.R
import org.xiao.kankan.home.adapter.CardAdapter
import org.xiao.kankan.home.adapter.FooterAdapter
import org.xiao.kankan.home.adapter.FooterView
import org.xiao.kankan.home.databinding.FeedFragmentBinding
import org.xiao.mvvm.MvvmFragment

class FeedFragment : MvvmFragment<FeedFragmentBinding, FeedViewModel>() {
    companion object {
        fun newInstance(): FeedFragment {
            return FeedFragment()
        }
    }

    private val cardAdapter by lazy {
        CardAdapter()
    }

    override fun getRootLayoutId(): Int {
        return R.layout.feed_fragment
    }

    override fun getViewModelClass(): Class<FeedViewModel> {
        return FeedViewModel::class.java
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        initObservers()
        mModel.loadData()
    }

    private fun initViews() {
        mBinding.recyclerview.layoutManager = LinearLayoutManager(context)
        cardAdapter.setLoadMoreListener(object : FooterAdapter.LoadMoreLisener {
            override fun onLoadMore() {
                Toast.makeText(context, "加载成功", Toast.LENGTH_SHORT).show()
                cardAdapter.updateLoadmoreState(FooterView.STATE_NORMAL)
            }
        })
        mBinding.recyclerview.adapter = cardAdapter
    }

    private fun initObservers() {
        mModel.mCardList.observe(viewLifecycleOwner, Observer {
            cardAdapter.initData(it.itemList)
        })
    }
}
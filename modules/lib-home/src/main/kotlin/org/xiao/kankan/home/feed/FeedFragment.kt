package org.xiao.kankan.home.feed

import android.os.Bundle
import android.os.Handler
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.feed_fragment.*
import org.xiao.kankan.home.R
import org.xiao.kankan.home.adapter.CardAdapter
import org.xiao.kankan.home.databinding.FeedFragmentBinding
import org.xiao.mvvm.MvvmFragment

class FeedFragment : MvvmFragment<FeedViewModel>() {
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
        recyclerview.layoutManager = LinearLayoutManager(context)
        recyclerview.setLoadMoreEnable(true)
        recyclerview.setLoadMoreListener(object : org.xiao.ui.loadmore.LoadMoreRecyclerView.LoadMoreListener {
            override fun onLoadMore() {
                if (mModel.loadMoreData()) {
                    recyclerview.setLoadMoreComplete()
                } else {
                    recyclerview.setNoMoreContent()
                }
            }
        })
        recyclerview.adapter = cardAdapter
    }

    private fun initObservers() {
        mModel.mCardList.observe(viewLifecycleOwner, Observer {
            cardAdapter.addDatas(it.itemList)
        })
    }
}
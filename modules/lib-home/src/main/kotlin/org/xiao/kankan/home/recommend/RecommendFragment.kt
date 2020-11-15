package org.xiao.kankan.home.recommend

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.recommend_fragment.*
import org.xiao.kankan.home.R
import org.xiao.kankan.home.adapter.CardAdapter
import org.xiao.mvvm.MvvmFragment
import org.xiao.ui.loadmore.LoadMoreRecyclerView

class RecommendFragment : MvvmFragment<RecommendViewModel>() {
    companion object {
        fun newInstance(): RecommendFragment {
            return RecommendFragment()
        }
    }

    private val cardAdapter by lazy {
        CardAdapter()
    }

    override fun getRootLayoutId(): Int {
        return R.layout.recommend_fragment
    }

    override fun getViewModelClass(): Class<RecommendViewModel> {
        return RecommendViewModel::class.java
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        initObservers()
        mModel.loadData()
    }

    private fun initViews() {
        recyclerview.layoutManager = LinearLayoutManager(context)
        recyclerview.adapter = cardAdapter
        recyclerview.setLoadMoreEnable(true)
        recyclerview.setLoadMoreListener(object : LoadMoreRecyclerView.LoadMoreListener {
            override fun onLoadMore() {
                if (mModel.loadMoreData()) {
                    recyclerview.setLoadMoreComplete()
                } else {
                    recyclerview.setNoMoreContent()
                }
            }
        })
    }

    private fun initObservers() {
        mModel.mCardList.observe(viewLifecycleOwner, Observer {
            cardAdapter.addDatas(it.itemList)
        })
    }
}
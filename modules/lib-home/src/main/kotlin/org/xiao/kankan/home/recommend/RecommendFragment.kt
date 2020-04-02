package org.xiao.kankan.home.recommend

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import org.xiao.kankan.home.R
import org.xiao.kankan.home.adapter.CardAdapter
import org.xiao.kankan.home.databinding.RecommendFragmentBinding
import org.xiao.mvvm.MvvmFragment
import org.xiao.ui.loadmore.LoadMoreRecyclerView

class RecommendFragment : MvvmFragment<RecommendFragmentBinding, RecommendViewModel>() {
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
        mBinding.recyclerview.layoutManager = LinearLayoutManager(context)
        mBinding.recyclerview.adapter = cardAdapter
        mBinding.recyclerview.setLoadMoreEnable(true)
        mBinding.recyclerview.setLoadMoreListener(object : LoadMoreRecyclerView.LoadMoreListener {
            override fun onLoadMore() {
                if (mModel.loadMoreData()) {
                    mBinding.recyclerview.setLoadMoreComplete()
                } else {
                    mBinding.recyclerview.setNoMoreContent()
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
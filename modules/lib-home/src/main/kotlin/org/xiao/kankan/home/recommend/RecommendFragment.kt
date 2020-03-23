package org.xiao.kankan.home.recommend

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.xiao.kaiyan.api.RetrofitManager
import org.xiao.kankan.home.R
import org.xiao.kankan.home.databinding.RecommendFragmentBinding
import org.xiao.mvvm.MvvmFragment

class RecommendFragment : MvvmFragment<RecommendFragmentBinding, RecommendViewModel>() {
    companion object {
        fun newInstance(): RecommendFragment {
            return RecommendFragment()
        }
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

    private fun initObservers() {
        mModel.mAllRec.observe(viewLifecycleOwner, Observer {
            mBinding.reconmendText.text = it.itemList.toString()
        })
    }

    private fun initViews() {
    }
}
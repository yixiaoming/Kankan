package org.xiao.kankan.home.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

open class FooterAdapter :
        RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    companion object {
        const val TYPE_FOOTER = -1
    }

    interface LoadMoreLisener {
        fun onLoadMore()
    }

    protected var adapter: RecyclerView.Adapter<RecyclerView.ViewHolder> = this
    protected var loadMoreState = FooterView.STATE_NORMAL
    protected var loadMoreListner: LoadMoreLisener? = null

    fun setLoadMoreListener(listener: LoadMoreLisener) {
        loadMoreListner = listener
    }

    fun updateLoadmoreState(state: Int) {
        loadMoreState = state
        notifyDataSetChanged()
    }

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val showLoadMore = !recyclerView.canScrollVertically(1) && dy > 0
                if (showLoadMore) {
                    if (loadMoreListner != null && loadMoreState != FooterView.STATE_LOADING) {
                        loadMoreState = FooterView.STATE_LOADING
                        loadMoreListner?.onLoadMore()
                    }
                }
            }
        })
    }

    override fun getItemViewType(position: Int): Int {
        if (position == adapter.itemCount) return TYPE_FOOTER
        return adapter.getItemViewType(position)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (viewType == TYPE_FOOTER) return FooterViewHolder.create(parent)
        return adapter.onCreateViewHolder(parent, viewType)
    }

    override fun getItemCount(): Int {
        return adapter.itemCount + if (loadMoreState == FooterView.STATE_NORMAL) 1 else 0
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is FooterViewHolder) {

        } else {
            adapter.onBindViewHolder(holder, position)
        }
    }
}
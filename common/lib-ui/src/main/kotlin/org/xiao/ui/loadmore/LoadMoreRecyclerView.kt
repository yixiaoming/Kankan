package org.xiao.ui.loadmore

import android.content.Context
import android.util.AttributeSet
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.GridLayoutManager.SpanSizeLookup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager


class LoadMoreRecyclerView @JvmOverloads constructor(context: Context, atts: AttributeSet? = null, defStyleAtr: Int = 0)
    : RecyclerView(context, atts, defStyleAtr) {

    companion object {
        const val TYPE_FOOTER = Int.MIN_VALUE
        const val ID_FOOTER = Long.MIN_VALUE
    }

    private var mLoadMoreEnable = false
    private var mWrapAdapter: WrapAdapter? = null
    private var mLoadMoreListener: LoadMoreListener? = null
    private var mDataObserver = DataObserver()
    private var mFooterView: org.xiao.ui.loadmore.LoadMoreFooter = LoadMoreFooter(getContext())

    interface LoadMoreListener {
        fun onLoadMore()
    }

    fun setLoadMoreListener(listener: LoadMoreListener) {
        mLoadMoreListener = listener
    }

    fun setLoadMoreEnable(enable: Boolean) {
        mLoadMoreEnable = enable
    }

    fun setLoadMoreComplete() {
        mFooterView.setState(LoadMoreFooter.STATE_NORMAL)
    }

    fun setNoMoreContent() {
        mFooterView.setState(LoadMoreFooter.STATE_NO_MORE)
    }

    override fun setAdapter(adapter: Adapter<ViewHolder>?) {
        mWrapAdapter = WrapAdapter(adapter!!)
        super.setAdapter(mWrapAdapter)
        adapter.registerAdapterDataObserver(mDataObserver)
        mDataObserver.onChanged()
    }

    override fun onScrollStateChanged(state: Int) {
        super.onScrollStateChanged(state)
        if (state == SCROLL_STATE_IDLE && mLoadMoreListener != null && mFooterView.getState() != LoadMoreFooter.STATE_LOADING && mLoadMoreEnable) {
            val lastVisibleItemPosition: Int
            lastVisibleItemPosition = when (layoutManager) {
                is GridLayoutManager -> {
                    (layoutManager as GridLayoutManager).findLastVisibleItemPosition()
                }
                is StaggeredGridLayoutManager -> {
                    val into = IntArray((layoutManager as StaggeredGridLayoutManager).spanCount)
                    (layoutManager as StaggeredGridLayoutManager).findLastVisibleItemPositions(into)
                    findMax(into)
                }
                else -> {
                    (layoutManager as LinearLayoutManager?)!!.findLastVisibleItemPosition()
                }
            }
            if (layoutManager!!.childCount > 0 && lastVisibleItemPosition >= layoutManager!!.itemCount - 1 && layoutManager!!.itemCount > layoutManager!!.childCount) {
                mFooterView.setState(LoadMoreFooter.STATE_LOADING)
                mLoadMoreListener?.onLoadMore()
            }
        }
    }

    private fun findMax(lastPositions: IntArray): Int {
        var max = lastPositions[0]
        for (value in lastPositions) {
            if (value > max) {
                max = value
            }
        }
        return max
    }

    inner class WrapAdapter(var adapter: Adapter<ViewHolder>) : RecyclerView.Adapter<ViewHolder>() {

        private fun isFooter(position: Int) = position == adapter.itemCount

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            if (viewType == TYPE_FOOTER) {
                return FooterViewHolder(mFooterView)
            }
            return adapter.onCreateViewHolder(parent, viewType)
        }

        override fun getItemViewType(position: Int): Int {
            if (position == adapter.itemCount) return TYPE_FOOTER
            return adapter.getItemViewType(position)
        }

        override fun getItemCount() = if (mLoadMoreEnable) adapter.itemCount + 1 else adapter.itemCount

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            if (position < adapter.itemCount) {
                adapter.onBindViewHolder(holder, position)
            }
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int, payloads: MutableList<Any>) {
            if (position < adapter.itemCount) {
                if (payloads.isEmpty()) {
                    adapter.onBindViewHolder(holder, position)
                } else {
                    adapter.onBindViewHolder(holder, position, payloads)
                }
            }
        }

        override fun getItemId(position: Int): Long {
            if (position < adapter.itemCount) {
                return adapter.getItemId(position)
            }
            return ID_FOOTER
        }

        override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
            super.onAttachedToRecyclerView(recyclerView)
            val layoutManager = recyclerView.layoutManager
            if (layoutManager is GridLayoutManager) {
                layoutManager.spanSizeLookup = object : SpanSizeLookup() {
                    override fun getSpanSize(position: Int): Int {
                        return if (isFooter(position)) layoutManager.spanCount else 1
                    }
                }
            }
            adapter.onAttachedToRecyclerView(recyclerView)
        }

        override fun onDetachedFromRecyclerView(recyclerView: RecyclerView) {
            adapter.onDetachedFromRecyclerView(recyclerView)
        }

        override fun onViewAttachedToWindow(holder: ViewHolder) {
            super.onViewAttachedToWindow(holder)
            val lp = holder.itemView.layoutParams
            if (lp != null && lp is StaggeredGridLayoutManager.LayoutParams
                    && isFooter(holder.layoutPosition)) {
                lp.isFullSpan = true
            }
            adapter.onViewAttachedToWindow(holder)
        }

        override fun onViewDetachedFromWindow(holder: ViewHolder) {
            adapter.onViewDetachedFromWindow(holder)
        }

        override fun onViewRecycled(holder: ViewHolder) {
            adapter.onViewRecycled(holder)
        }

        override fun onFailedToRecycleView(holder: ViewHolder): Boolean {
            return adapter.onFailedToRecycleView(holder)
        }

        override fun unregisterAdapterDataObserver(observer: AdapterDataObserver) {
            adapter.unregisterAdapterDataObserver(observer)
        }

        override fun registerAdapterDataObserver(observer: AdapterDataObserver) {
            adapter.registerAdapterDataObserver(observer)
        }
    }

    inner class DataObserver : AdapterDataObserver() {
        override fun onChanged() {
            mWrapAdapter?.notifyDataSetChanged()
        }

        override fun onItemRangeRemoved(positionStart: Int, itemCount: Int) {
            mWrapAdapter?.notifyItemRangeRemoved(positionStart, itemCount)
        }

        override fun onItemRangeMoved(fromPosition: Int, toPosition: Int, itemCount: Int) {
            mWrapAdapter?.notifyItemMoved(fromPosition, toPosition)
        }

        override fun onItemRangeInserted(positionStart: Int, itemCount: Int) {
            mWrapAdapter?.notifyItemRangeInserted(positionStart, itemCount)
        }

        override fun onItemRangeChanged(positionStart: Int, itemCount: Int) {
            mWrapAdapter?.notifyItemRangeChanged(positionStart, itemCount)
        }

        override fun onItemRangeChanged(positionStart: Int, itemCount: Int, payload: Any?) {
            mWrapAdapter?.notifyItemRangeChanged(positionStart, itemCount, payload)
        }
    }
}
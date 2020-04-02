package org.xiao.ui.loadmore

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import kotlinx.android.synthetic.main.loadmore_footer.view.*
import org.xiao.ui.R

class LoadMoreFooter @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0)
    : FrameLayout(context, attrs, defStyleAttr) {

    companion object {
        const val STATE_NORMAL = 0
        const val STATE_LOADING = 1
        const val STATE_NO_MORE = 2
    }

    private var mState = STATE_NORMAL

    init {
        LayoutInflater.from(context).inflate(R.layout.loadmore_footer, this)
    }

    fun setState(state: Int) {
        mState = state
        when (mState) {
            STATE_NORMAL -> {
                setText("加载更多")
            }
            STATE_LOADING -> {
                setText("加载中...")
            }
            STATE_NO_MORE -> {
                setText("已经到底了")
            }
        }
    }

    fun getState(): Int {
        return mState
    }

    private fun setText(text: String) {
        loadingText.text = text
    }
}
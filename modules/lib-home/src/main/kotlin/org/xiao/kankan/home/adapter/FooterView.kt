package org.xiao.kankan.home.adapter

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import android.widget.TextView
import org.xiao.kankan.home.R

class FooterView(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0)
    : FrameLayout(context, attrs, defStyleAttr) {

    companion object {
        const val STATE_NORMAL = 0
        const val STATE_NO_MORE = 1
        const val STATE_LOADING = 2
        const val STATE_ERROR = 3
    }

    private var state: Int = STATE_NORMAL
    private lateinit var text: TextView

    init {
        LayoutInflater.from(context).inflate(R.layout.item_card_load_more, this)
        text = findViewById(R.id.loadmore_text)
    }

    fun changeState(state: Int) {
        this.state = state
    }
}
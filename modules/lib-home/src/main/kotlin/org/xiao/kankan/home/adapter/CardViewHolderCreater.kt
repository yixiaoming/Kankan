package org.xiao.kankan.home.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

object CardViewHolderCreater {
    private const val CARD_TYPE_INVALID = 0
    private const val CARD_TYPE_TEXT = 1
    private const val CARD_TYPE_FOLLOW = 2

    private val typeMap: MutableMap<String, Int> = mutableMapOf()

    init {
        typeMap["invalidCard"] = CARD_TYPE_INVALID
        typeMap["textCard"] = CARD_TYPE_TEXT
        typeMap["followCard"] = CARD_TYPE_FOLLOW
    }

    fun getType(typeString: String): Int {
        return typeMap[typeString] ?: CARD_TYPE_INVALID
    }

    fun createViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            CARD_TYPE_TEXT -> {
                TextCardViewHolder.create(parent)
            }
            CARD_TYPE_FOLLOW -> {
                FollowCardViewHolder.create(parent)
            }
            else -> {
                InvalidViewHolder.create(parent)
            }
        }
    }
}
package org.xiao.kankan.home.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

object CardViewHolderCreater {
    private const val CARD_TYPE_INVALID = 0
    private const val CARD_TYPE_TEXT = 1
    private const val CARD_TYPE_FOLLOW = 2
    private const val CARD_INFORMATION = 3
    private const val CARD_SMALL_VIDEO = 4
    private const val CARD_BRIEF_CARD = 5
    private const val CARD_BANNER = 6

    private val typeMap: MutableMap<String, Int> = mutableMapOf()

    init {
        typeMap["invalidCard"] = CARD_TYPE_INVALID
        typeMap["textCard"] = CARD_TYPE_TEXT
        typeMap["followCard"] = CARD_TYPE_FOLLOW
        typeMap["informationCard"] = CARD_INFORMATION
        typeMap["videoSmallCard"] = CARD_SMALL_VIDEO
        typeMap["briefCard"] = CARD_BRIEF_CARD
        typeMap["banner"] = CARD_BANNER
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
            CARD_INFORMATION -> {
                InformationViewHolder.create(parent)
            }
            CARD_SMALL_VIDEO -> {
                SmallVideoViewHolder.create(parent)
            }
            CARD_BRIEF_CARD -> {
                BriefCardViewHolder.create(parent)
            }
            CARD_BANNER -> {
                BannerViewHolder.create(parent)
            }
            else -> {
                InvalidViewHolder.create(parent)
            }
        }
    }
}
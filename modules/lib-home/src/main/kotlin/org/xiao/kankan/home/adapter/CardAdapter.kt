package org.xiao.kankan.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.view.menu.MenuView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.annotation.GlideModule
import org.xiao.kaiyan.entity.Card
import org.xiao.kankan.home.R
import org.yxm.glide.GlideApp

class CardAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        const val CARD_TYPE_INVALID = -1
        const val CARD_TYPE_TEXT = 1
        const val CARD_TYPE_FOLLOW = 2
    }

    private var cards: MutableList<Card> = mutableListOf()

    fun initData(newData: List<Card>) {
        cards.clear()
        cards.addAll(newData)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            CARD_TYPE_TEXT -> {
                TextCardViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_text_card, parent, false))
            }
            CARD_TYPE_FOLLOW -> {
                FollowCardViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_follow_card, parent, false))
            }
            else -> {
                InvalidViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_invalid_card, parent, false))
            }
        }
    }

    override fun getItemCount(): Int {
        return cards.size
    }

    override fun getItemViewType(position: Int): Int {
        return when (cards[position].type) {
            "textCard" -> CARD_TYPE_TEXT
            "followCard" -> CARD_TYPE_FOLLOW
            else -> CARD_TYPE_INVALID
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = cards[position]
        when (holder) {
            is TextCardViewHolder -> {
                holder.title.text = item.data?.text
                holder.rightText.text = item.data?.rightText
            }
            is FollowCardViewHolder -> {
                holder.title.text = item.data?.header?.title
                holder.label.text = item.data?.header?.description

                GlideApp.with(holder.cover)
                        .load(item.data?.content?.data?.cover?.feed)
                        .centerCrop()
                        .into(holder.cover)

                GlideApp.with(holder.authorImg)
                        .load(item.data?.header?.icon)
                        .centerCrop()
                        .into(holder.authorImg)
            }
        }
    }
}
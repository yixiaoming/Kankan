package org.xiao.kankan.home.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_banner_card.view.*
import org.xiao.kaiyan.entity.Card
import org.xiao.kankan.home.R
import org.yxm.glide.GlideApp

class BannerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), ICardViewHolder {
    private val cover = itemView.cover

    override fun bind(card: Card) {
        GlideApp.with(cover)
                .load(card.data?.image)
                .centerCrop()
                .into(cover)
    }

    companion object {
        fun create(parent: ViewGroup): BannerViewHolder {
            val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_banner_card, parent, false)
            return BannerViewHolder(itemView)
        }
    }
}
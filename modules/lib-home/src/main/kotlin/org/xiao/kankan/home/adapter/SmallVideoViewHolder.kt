package org.xiao.kankan.home.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_small_video.view.*
import org.xiao.kaiyan.entity.Card
import org.xiao.kankan.home.R
import org.yxm.glide.GlideApp

class SmallVideoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), ICardViewHolder {
    private val cover = itemView.cover
    private val title = itemView.title
    private val category = itemView.category

    override fun bind(card: Card) {
        GlideApp.with(cover)
                .load(card.data?.cover?.feed)
                .centerCrop()
                .into(cover)
        title.text = card.data?.description
        category.text = "# ${card.data?.category}"
    }

    companion object {
        fun create(parent: ViewGroup): RecyclerView.ViewHolder {
            val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_small_video, parent, false)
            return SmallVideoViewHolder(itemView)
        }
    }
}
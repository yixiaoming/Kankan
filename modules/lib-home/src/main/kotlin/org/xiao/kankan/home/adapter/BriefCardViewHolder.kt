package org.xiao.kankan.home.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_brief_card.view.*
import org.xiao.kaiyan.entity.Card
import org.xiao.kankan.home.R
import org.yxm.glide.GlideApp

class BriefCardViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), ICardViewHolder {
    private val cover = itemView.cover
    private val title = itemView.title
    private val desc = itemView.description

    override fun bind(card: Card) {
        GlideApp.with(cover)
                .load(card.data?.icon)
                .centerCrop()
                .into(cover)
        title.text = card.data?.title
        desc.text = card.data?.description
    }

    companion object {
        fun create(parent: ViewGroup): RecyclerView.ViewHolder {
            val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_brief_card, parent, false)
            return BriefCardViewHolder(itemView)
        }
    }
}
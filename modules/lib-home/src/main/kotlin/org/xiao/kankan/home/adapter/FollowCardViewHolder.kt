package org.xiao.kankan.home.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_follow_card.view.*
import org.xiao.kaiyan.entity.Card
import org.xiao.kankan.home.R
import org.yxm.glide.GlideApp

class FollowCardViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), ICardViewHolder {
    private val cover: ImageView = itemView.cover
    private val authorImg: ImageView = itemView.authorImg
    private val title: TextView = itemView.title
    private val label: TextView = itemView.label

    override fun bind(card: Card) {
        title.text = card.data?.header?.title
        label.text = card.data?.header?.description

        GlideApp.with(cover)
                .load(card.data?.content?.data?.cover?.feed)
                .centerCrop()
                .into(cover)

        GlideApp.with(authorImg)
                .load(card.data?.header?.icon)
                .centerCrop()
                .into(authorImg)
    }

    companion object {
        fun create(parent: ViewGroup): RecyclerView.ViewHolder {
            val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_follow_card, parent, false)
            return FollowCardViewHolder(itemView)
        }
    }

}
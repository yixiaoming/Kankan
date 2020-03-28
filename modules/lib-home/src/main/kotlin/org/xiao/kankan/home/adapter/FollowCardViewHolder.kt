package org.xiao.kankan.home.adapter

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_follow_card.view.*

class FollowCardViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val cover: ImageView = itemView.cover
    val authorImg: ImageView = itemView.authorImg
    val title: TextView = itemView.title
    val label: TextView = itemView.label
}
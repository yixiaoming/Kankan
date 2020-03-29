package org.xiao.kankan.home.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_information_card.view.*
import org.xiao.kaiyan.entity.Card
import org.xiao.kankan.home.R
import org.yxm.glide.GlideApp

class InformationViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), ICardViewHolder {
    private val cover: ImageView = itemView.cover
    private val title: TextView = itemView.title

    override fun bind(card: Card) {
        GlideApp.with(cover)
                .load(card.data?.backgroundImage)
                .centerInside()
                .into(cover)
        val titles = card.data?.titleList?.joinToString("\n")
        title.text = titles
    }

    companion object {
        fun create(parent: ViewGroup): RecyclerView.ViewHolder {
            val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_information_card, parent, false)
            return InformationViewHolder(itemView)
        }
    }
}
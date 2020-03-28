package org.xiao.kankan.home.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import org.xiao.kaiyan.entity.Card
import org.xiao.kankan.home.R

class InvalidViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), ICardViewHolder {
    override fun bind(card: Card) {
    }

    companion object {
        fun create(parent: ViewGroup): RecyclerView.ViewHolder {
            val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_invalid_card, parent, false)
            return InvalidViewHolder(itemView)
        }
    }
}
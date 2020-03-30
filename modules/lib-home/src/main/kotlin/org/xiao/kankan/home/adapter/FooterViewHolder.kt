package org.xiao.kankan.home.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import org.xiao.kankan.home.R

class FooterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    companion object {
        fun create(parent: ViewGroup): RecyclerView.ViewHolder {
            val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_card_load_more, parent, false)
            return FooterViewHolder(itemView)
        }
    }
}
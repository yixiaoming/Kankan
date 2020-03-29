package org.xiao.kankan.home.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_text_card.view.*
import org.xiao.kaiyan.entity.Card
import org.xiao.kankan.home.R

class TextCardViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), ICardViewHolder {
    private val title: TextView = itemView.title
    private val rightText: TextView = itemView.rightText

    override fun bind(card: Card) {
        when (card.data?.type) {
            "footer3" -> {
                title.text = ""
                rightText.text = "${card.data?.text} >"
            }
            "footer2" -> {
                title.text = ""
                rightText.text = card.data?.text
            }
            else -> {
                title.text = card.data?.text
                rightText.text = card.data?.rightText
            }
        }

    }

    companion object {
        fun create(parent: ViewGroup): RecyclerView.ViewHolder {
            val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_text_card, parent, false)
            return TextCardViewHolder(itemView)
        }
    }
}
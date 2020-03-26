package org.xiao.kankan.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import org.xiao.kaiyan.entity.Card
import org.xiao.kankan.home.R

class CardAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        const val CARD_TYPE_INVALID = -1
        const val CARD_TYPE_TEXT = 1
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
                TextViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_text_card, parent, false))
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
            else -> CARD_TYPE_INVALID
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = cards[position]
        when (holder) {
            is TextViewHolder -> {
                holder.title.text = item.data.text

                if ("" != item.data.rightText) {
                    holder.rightText.text = item.data.rightText
                }
            }
        }
    }

}
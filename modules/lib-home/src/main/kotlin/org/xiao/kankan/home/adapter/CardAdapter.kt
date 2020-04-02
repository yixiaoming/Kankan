package org.xiao.kankan.home.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import org.xiao.kaiyan.entity.Card

class CardAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var cards: MutableList<Card> = mutableListOf()

    fun initData(newData: List<Card>) {
        cards.clear()
        cards.addAll(newData)
        notifyDataSetChanged()
    }

    fun addDatas(newDatas: List<Card>) {
        cards.addAll(newDatas)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return CardViewHolderCreater.createViewHolder(parent, viewType)
    }

    override fun getItemViewType(position: Int): Int {
        return CardViewHolderCreater.getType(cards[position].type)
    }

    override fun getItemCount(): Int {
        return cards.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = cards[position]
        when (holder) {
            is ICardViewHolder -> {
                holder.bind(item)
            }
            else -> {
                throw IllegalStateException("Your VewHolder must implement ICrdViewHolder")
            }
        }
    }
}
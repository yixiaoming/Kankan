package org.xiao.kaiyan.entity

data class CardList(
        val count: Int = 0,
        val itemList: MutableList<Card> = mutableListOf(),
        val nextPageUrl: String = "",
        val total: Int = 0
)
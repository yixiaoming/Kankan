package org.xiao.kaiyan.entity

data class CardList(
        val count: Int = 0,
        val itemList: List<Card> = emptyList(),
        val nextPageUrl: String = "",
        val total: Int = 0
)
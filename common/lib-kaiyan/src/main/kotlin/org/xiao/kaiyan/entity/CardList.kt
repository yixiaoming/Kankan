package org.xiao.kaiyan.entity

data class CardList(
        val adExist: Boolean,
        val count: Int,
        val itemList: List<Card>,
        val nextPageUrl: String,
        val total: Int
)
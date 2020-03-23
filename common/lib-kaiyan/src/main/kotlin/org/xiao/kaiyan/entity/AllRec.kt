package org.xiao.kaiyan.entity

data class AllRec(
        val adExist: Boolean,
        val count: Int,
        val itemList: List<AllRecItem>,
        val nextPageUrl: String,
        val total: Int
)
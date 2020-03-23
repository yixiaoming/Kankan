package org.xiao.kaiyan.entity

import java.util.*

data class Header(
        val actionUrl: String,
        val cover: Cover,
        val description: String,
        val icon: String,
        val iconType: String,
        val id: Int,
        val label: String,
        val labelList: List<String>,
        val rightText: String,
        val showHateVideo: Boolean,
        val subTitle: String,
        val textAlign: String,
        val time: Long,
        val title: String
)
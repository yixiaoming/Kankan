package org.xiao.kaiyan.entity

data class Header(
        val actionUrl: String = "",
        val cover: Cover = Cover(),
        val description: String = "",
        val icon: String = "",
        val iconType: String = "",
        val id: Int = 0,
        val rightText: String = "",
        val showHateVideo: Boolean = false,
        val subTitle: String = "",
        val textAlign: String = "",
        val time: Long = 0,
        val title: String = ""
)
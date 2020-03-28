package org.xiao.kaiyan.entity

data class PlayInfo(
        val height: Int = 0,
        val name: String = "",
        val type: String = "",
        val url: String = "",
        val urlList: List<Url> = emptyList(),
        val width: Int = 0
)
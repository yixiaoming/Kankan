package org.xiao.kaiyan.entity

data class Follow(
    val followed: Boolean = false,
    val itemId: Int = 0,
    val itemType: String = ""
)
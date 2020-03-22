package org.xiao.kaiyan.entity

data class Follow(
    val followed: Boolean,
    val itemId: Int,
    val itemType: String
)
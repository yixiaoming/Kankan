package org.xiao.kaiyan.entity

data class Tag(
    val actionUrl: String,
    val bgPicture: String,
    val communityIndex: Int,
    val desc: String,
    val haveReward: Boolean,
    val headerImage: String,
    val id: Int,
    val ifNewest: Boolean,
    val name: String,
    val tagRecType: String
)
package org.xiao.kaiyan.entity

import java.util.*

data class Tag(
        val actionUrl: String,
        val adTrack: String,
        val bgPicture: String,
        val childTagIdList: List<Int>,
        val childTagList: List<Tag>,
        val communityIndex: Int,
        val desc: String,
        val haveReward: Boolean,
        val headerImage: String,
        val id: Int,
        val ifNewest: Boolean,
        val name: String,
        val newestEndTime: Date,
        val tagRecType: String
)
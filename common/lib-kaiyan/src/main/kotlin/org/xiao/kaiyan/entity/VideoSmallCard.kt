package org.xiao.kaiyan.entity

import java.util.*

data class VideoSmallCard(val data: VideoData,
                          override val id: Int,
                          override val type: String,
                          override val tag: String,
                          override val adIndex: Int = -1)
    : CardItem(id, type, tag, adIndex) {
}
package org.xiao.kaiyan.entity

import java.util.*

data class FollowCard(val data: Data,
                      override val id: Int,
                      override val type: String,
                      override val tag: String,
                      override val adIndex: Int = -1)
    : CardItem(id, type, tag, adIndex) {
    data class Data(
            val dataType: String,
            val header: Header,
            val content: Content) {
        data class Header(val id: Int,
                          val title: String,
                          var font: String,
                          var subTitle: String,
                          var subTitleFont: String,
                          val textAlign: String,
                          var cover: String,
                          var label: String,
                          val actionUrl: String,
                          var labelList: String,
                          var rightText: String,
                          val icon: String,
                          val iconType: String,
                          val description: String,
                          val time: Int,
                          val showHateVideo: Boolean)

        data class Content(
                val type: String,
                val data: VideoData)
    }
}
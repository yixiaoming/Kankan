package org.xiao.kaiyan.entity

import java.util.*

data class VideoData(val dataType: String,
                     val id: Int,
                     val title: String,
                     val description: String,
                     val tags: List<Tag>,
                     val consumption: Consumption,
                     val resourceType: String,
                     val slogan: String,
                     val provider: Provider,
                     val category: String,
                     val author: Author,
                     val cover: Cover,
                     val playUrl: String,
                     val thumbPlayUrl: String,
                     val duration: Int,
                     val webUrl: WebUrl,
                     val releaseTime: Date,
                     val date: Date,
                     val label: String,
                     val descriptionEditor: String,
                     val library: String,
                     val type: String,
                     val ad: Boolean,
                     val played: Boolean,
                     val collected: Boolean,
                     val descriptionPgc: String) {
}
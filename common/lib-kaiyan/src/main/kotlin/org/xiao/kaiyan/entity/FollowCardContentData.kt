package org.xiao.kaiyan.entity

import java.util.*

data class FollowCardContentData(
    val ad: Boolean,
    val author: Author,
    val category: String,
    val collected: Boolean,
    val consumption: Consumption,
    val cover: Cover,
    val dataType: String,
    val date: Long,
    val description: String,
    val descriptionEditor: String,
    val descriptionPgc: String,
    val duration: Int,
    val id: Int,
    val label: String,
    val labelList: List<String>,
    val library: String,
    val playInfo: List<PlayInfo>,
    val playUrl: String,
    val played: Boolean,
    val provider: Provider,
    val reallyCollected: Boolean,
    val releaseTime: Long,
    val resourceType: String,
    val searchWeight: Int,
    val slogan: String,
    val subtitles: List<String>,
    val tags: List<Tag>,
    val thumbPlayUrl: String,
    val title: String,
    val titlePgc: String,
    val type: String,
    val webUrl: WebUrl
)
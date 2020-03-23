package org.xiao.kaiyan.entity

data class UgcSelectedCardData(
        val dataType: String,
        val cover: Cover,
        val dailyResource: Boolean,
        val id: Int,
        val nickname: String,
        val resourceType: String,
        val url: String,
        val urls: List<String>,
        val userCover: String,

        val actionUrl: String,
        val autoPlay: Boolean,
        val description: String,
        val header: HorizontScrollCardHeader,
        val image: String,
        val label: Label,
        val labelList: List<String>,
        val shade: Boolean,
        val title: String
)
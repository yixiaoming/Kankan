package org.xiao.kaiyan.entity

data class TextCard(val data: Data,
                    override val id: Int,
                    override val type: String,
                    override val tag: String,
                    override val adIndex: Int = -1)
    : CardItem(id, type, tag, adIndex) {
    data class Data(val id: Int,
                    val dataType: String,
                    val type: String,
                    val text: String,
                    val subTitle: String,
                    val actionUrl: String,
                    val adTrack: String,
                    val follow: String,
                    val rightText: String)
}
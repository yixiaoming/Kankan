package org.xiao.kaiyan.entity

class UgcSelectedCard(val data: Data,
                      override val id: Int,
                      override val type: String,
                      override val tag: String,
                      override val adIndex: Int = -1)
    : CardItem(id, type, tag, adIndex) {
    data class Data(
            val dataType: String,
            val header: Header,
            val itemList: List<Data>,
            val count:Int
    ) {
        data class Header(val actionUrl: String,
                          val cover: Cover,
                          val description: String,
                          val font: String,
                          val icon: String,
                          val iconType: String,
                          val id: Int,
                          val label: String,
                          val labelList: String,
                          val rightText: String,
                          val showHateVideo: Boolean,
                          val subTitle: String,
                          val subTitleFont: String,
                          val textAlign: String,
                          val time: Long,
                          val title: String)

        data class Data(val cover: Cover,
                        val dailyResource: Boolean,
                        val dataType: String,
                        val id: Int,
                        val nickname: String,
                        val resourceType: String,
                        val url: String,
                        val urls: List<String>,
                        val userCover: String)
    }
}
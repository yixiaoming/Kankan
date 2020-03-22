package org.xiao.kaiyan.entity

class InformationCard(val data: Data,
                      override val id: Int,
                      override val type: String,
                      override val tag: String,
                      override val adIndex: Int = -1)
    : CardItem(id, type, tag, adIndex) {
    data class Data(
            val dataType: String,
            val id: Int,
            val titleList: List<String>,
            val backgroundImage: String,
            val actionUrl: String
    )
}
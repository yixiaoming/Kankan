package org.xiao.kaiyan.entity

data class Author(
        val description: String = "",
        val expert: Boolean = false,
        val follow: Follow = Follow(),
        val icon: String = "",
        val id: Int = 0,
        val link: String = "",
        val name: String = "",
        val recSort: Int = 0,
        val videoNum: Int = 0
)
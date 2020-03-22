package org.xiao.kaiyan.api

import org.xiao.kaiyan.entity.TabInfo

class KaiyanApi {

    fun getTabInfoList(): List<TabInfo> {
        return mutableListOf<TabInfo>().apply {
            add(TabInfo(-1, "发现", "http://baobab.kaiyanapp.com/api/v7/index/tab/discovery"))
            add(TabInfo(-2, "推荐", "http://baobab.kaiyanapp.com/api/v5/index/tab/allRec?page=0"))
            add(TabInfo(-3, "日报", "http://baobab.kaiyanapp.com/api/v5/index/tab/feed"))
        }
    }
}